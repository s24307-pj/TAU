package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        PrimeChecker primeChecker = new PrimeChecker();
        System.out.println("3 jest liczbą pierwszą?  " + primeChecker.isPrime(3));
    }
}