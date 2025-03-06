package ru.itmo.tpo.lab1.part1;


public class Secant {
    private static final int TERMS = 10;

    private Secant() { }

    public static double calc(double x) {
        x = Math.IEEEremainder(x, 2 * Math.PI);

        double cosX = 0;
        for (int n = 0; n < TERMS; n++) {
            double term = Math.pow(-1, n) * Math.pow(x, 2 * n) / factorial(2 * n);
            cosX += term;
        }

        if (isZero(cosX)) {
            return Double.POSITIVE_INFINITY;
        }
        return 1 / cosX;
    }

    private static long factorial(int n) {
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    private static boolean isZero(double x) {
        double eps = 1E-10;
        return Math.abs(x) <= eps;
    }
}
