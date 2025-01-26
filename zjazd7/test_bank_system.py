import pytest
import pytest_asyncio
from unittest.mock import patch, AsyncMock
from bank_system import Account, Bank, InsufficientFundsError


@pytest.fixture
def bank():
    return Bank()


@pytest.fixture
def account(bank):
    bank.create_account("123456", "John Doe", 100.0)
    return bank.get_account("123456")


def test_deposit(account):
    account.deposit(50.0)
    assert account.balance == 150.0


def test_withdraw(account):
    account.withdraw(30.0)
    assert account.balance == 70.0


def test_withdraw_insufficient_funds(account):
    with pytest.raises(InsufficientFundsError):
        account.withdraw(200.0)


@pytest.mark.asyncio
async def test_transfer(account):
    bank = Bank()
    bank.create_account("654321", "Jane Doe", 50.0)
    to_account = bank.get_account("654321")

    await account.transfer(to_account, 50.0)
    assert account.balance == 50.0
    assert to_account.balance == 100.0


@pytest.mark.asyncio
async def test_transfer_insufficient_funds(account):
    bank = Bank()
    bank.create_account("654321", "Jane Doe", 50.0)
    to_account = bank.get_account("654321")

    with pytest.raises(InsufficientFundsError):
        await account.transfer(to_account, 200.0)


def test_create_account(bank):
    bank.create_account("789012", "Alice Smith", 200.0)
    assert bank.get_account("789012").owner == "Alice Smith"


def test_get_account_not_found(bank):
    with pytest.raises(ValueError):
        bank.get_account("nonexistent")


@patch('bank_system.Account.transfer', new_callable=AsyncMock)
@pytest.mark.asyncio
async def test_process_transaction(mock_transfer, bank, account):
    bank.create_account("654321", "Jane Doe", 50.0)
    to_account = bank.get_account("654321")

    async def transaction():
        await account.transfer(to_account, 30.0)

    await bank.process_transaction(transaction)
    mock_transfer.assert_awaited_once_with(to_account, 30.0)