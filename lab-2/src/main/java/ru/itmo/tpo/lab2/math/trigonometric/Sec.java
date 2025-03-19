package ru.itmo.tpo.lab2.math.trigonometric;

import lombok.RequiredArgsConstructor;
import ru.itmo.tpo.lab2.math.MathFunction;

@RequiredArgsConstructor
public class Sec implements MathFunction {
    private final Cos cos;

    @Override
    public double calc(double x) {
        return 1 / cos.calc(x);
    }
}
