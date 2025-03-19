package ru.itmo.tpo.lab2.math.logariphmic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.itmo.tpo.lab2.math.MathFunction;

public class Ln implements MathFunction {
    private static final int MAX_ITERATIONS = 1_000_000;
    private static final double EPS = 1E-10;
    private static final Logger log = LogManager.getLogger(Ln.class);

    private Double cachedLn2;

    @Override
    public double calc(double x) {
        if (x <= 0) {
            throw new IllegalArgumentException("x should be positive. x=" + x);
        }

        if (x < 0.5) {
            return -calc(1 / x);
        }

        double lnY = 0;

        while (x > 2) {
            x /= 2;
            lnY += ln2();
        }

        double t = x - 1;
        double lnT = 0;
        double term = t;
        int n = 1;

        while (n < MAX_ITERATIONS && Math.abs(term) > EPS) {
            lnT += term / n;
            term *= -t;
            n++;
        }

        if (n == MAX_ITERATIONS) {
            log.info("Max iterations");
        }

        return lnY + lnT;
    }

    private double ln2() {
        if (cachedLn2 == null) {
            cachedLn2 = calc(2);
        }
        return cachedLn2;
    }
}
