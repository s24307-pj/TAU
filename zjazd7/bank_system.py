class InsufficientFundsError(Exception):
    """Exception raised for errors in the withdrawal process due to insufficient funds."""
    pass

class Account:
    def __init__(self, account_number: str, owner: str, initial_balance: float = 0.0):
        self.balance = initial_balance
        self.account_number = account_number
        self.owner = owner

    def deposit(self, amount: float):
        if amount <= 0:
            raise ValueError("Deposit amount must be positive.")
        self.balance += amount

    def withdraw(self, amount: float):
        if amount > self.balance:
            raise InsufficientFundsError("Insufficient funds for withdrawal.")
        self.balance -= amount

    async def transfer(self, to_account: 'Account', amount: float):
        if amount > self.balance:
            raise InsufficientFundsError("Insufficient funds for transfer.")
        self.withdraw(amount)
        to_account.deposit(amount)

class Bank:
    def __init__(self):
        self.accounts = {}

    def create_account(self, account_number: str, owner: str, initial_balance: float = 0.0):
        if account_number in self.accounts:
            raise ValueError("Account number already exists.")
        self.accounts[account_number] = Account(account_number, owner, initial_balance)

    def get_account(self, account_number: str) -> Account:
        if account_number not in self.accounts:
            raise ValueError("Account not found.")
        return self.accounts[account_number]

    async def process_transaction(self, transaction_func):
        return await transaction_func()