package ru.itmo.tpo.lab2.math.logariphmic;

import ru.itmo.tpo.lab2.math.MathFunction;

public class Log implements MathFunction {
    private final int base;
    private final Ln ln;

    public Log(int base, Ln ln) {
        if (base <= 0 || base == 1) {
            throw new IllegalArgumentException("base should be positive and shouldn't be equal 1");
        }
        this.base = base;
        this.ln = ln;
    }

    @Override
    public double calc(double x) {
        return ln.calc(x) / ln.calc(base);
    }
}
