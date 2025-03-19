package ru.itmo.tpo.lab2.math;

public class MathUtils {
    private static final Double EPS = 1E-10;

    private MathUtils() { }

    public static boolean isEqual(double a, double b) {
        return Math.abs(a - b) < EPS;
    }

    public static boolean isZero(double a) {
        return isEqual(a, 0);
    }

    public static long factorial(int n) {
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}
