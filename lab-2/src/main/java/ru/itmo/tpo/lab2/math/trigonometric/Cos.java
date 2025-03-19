package ru.itmo.tpo.lab2.math.trigonometric;

import ru.itmo.tpo.lab2.math.MathFunction;
import ru.itmo.tpo.lab2.math.MathUtils;

public class Cos implements MathFunction {
    private static final int TERMS = 10;

    @Override
    public double calc(double x) {
        x = Math.IEEEremainder(x, 2 * Math.PI);

        double cosX = 0;
        for (int n = 0; n < TERMS; n++) {
            double term = Math.pow(-1, n) * Math.pow(x, 2 * n) / MathUtils.factorial(2 * n);
            cosX += term;
        }
        return cosX;
    }
}
