package ru.itmo.tpo.lab2.math.trigonometric;

import lombok.RequiredArgsConstructor;
import ru.itmo.tpo.lab2.math.MathFunction;
import ru.itmo.tpo.lab2.math.MathUtils;

@RequiredArgsConstructor
public class Cot implements MathFunction {
    private final Cos cos;

    @Override
    public double calc(double x) {
        if (MathUtils.isZero(x % Math.PI)) {
            throw new IllegalArgumentException(String.format("cot is not exists for x=%.3f", x));
        }
        return cos.calc(x) / cos.calc(Math.PI / 2 - x);
    }
}
