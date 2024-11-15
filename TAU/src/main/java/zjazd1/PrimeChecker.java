package zjazd1;

public class PrimeChecker {
    public boolean isPrime(Object input) {
        if (!(input instanceof Integer)) {
            throw new IllegalArgumentException("Dane wejściowe muszą być liczbą całkowitą.");
        }

        int number = (int) input;

        if (number <= 1) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}

