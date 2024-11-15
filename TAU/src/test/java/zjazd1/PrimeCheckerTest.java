package zjazd1;

import zjazd1.PrimeChecker;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PrimeCheckerTest {

    PrimeChecker primeChecker = new PrimeChecker();

    @Test
    void testPositivePrimeNumber() {
        assertTrue(primeChecker.isPrime(5), "5 powinno być liczbą pierwszą");
    }

    @Test
    void testPositiveNonPrimeNumber() {
        assertFalse(primeChecker.isPrime(10), "10 nie powinno być liczbą pierwszą");
    }

    @Test
    void testNegativeNumber() {
        assertFalse(primeChecker.isPrime(-3), "-3 nie powinno być liczbą pierwszą");
    }

    @Test
    void testZero() {
        assertFalse(primeChecker.isPrime(0), "0 nie powinno być liczbą pierwszą");
    }

    @Test
    void testFloatingPointNumber() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> primeChecker.isPrime(3.14), 
                                           "Powinien być rzucony wyjątek dla liczby zmiennoprzecinkowej");
        assertEquals("Dane wejściowe muszą być liczbą całkowitą.", exception.getMessage());
    }

    @Test
    void testStringInput() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> primeChecker.isPrime("Hello"), 
                                           "Powinien być rzucony wyjątek dla typu String");
        assertEquals("Dane wejściowe muszą być liczbą całkowitą.", exception.getMessage());
    }

    @Test
    void testSingleCharacterInput() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> primeChecker.isPrime('A'), 
                                           "Powinien być rzucony wyjątek dla pojedynczej litery");
        assertEquals("Dane wejściowe muszą być liczbą całkowitą.", exception.getMessage());
    }

    @Test
    void testNullInput() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> primeChecker.isPrime(null), 
                                           "Powinien być rzucony wyjątek dla wartości null");
        assertEquals("Dane wejściowe muszą być liczbą całkowitą.", exception.getMessage());
    }

    @Test
    void testLargeNumber() {
        assertTrue(primeChecker.isPrime(Integer.MAX_VALUE), "Maksymalna wartość Integera powinna być liczbą pierwszą");
    }

    @Test
    void testSmallNegativeNumber() {
        assertFalse(primeChecker.isPrime(Integer.MIN_VALUE), "Minimalna wartość Integera nie powinna być liczbą pierwszą");
    }
}
