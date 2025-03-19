package ru.itmo.tpo.lab2.math;

import lombok.RequiredArgsConstructor;
import ru.itmo.tpo.lab2.math.logariphmic.*;
import ru.itmo.tpo.lab2.math.trigonometric.Cos;
import ru.itmo.tpo.lab2.math.trigonometric.Cot;
import ru.itmo.tpo.lab2.math.trigonometric.Sec;

@RequiredArgsConstructor
public class FuncSystem implements MathFunction {
    private final Cos cos;
    private final Cot cot;
    private final Sec sec;
    private final Ln ln;
    private final Log log2;
    private final Log log3;
    private final Log log5;
    private final Log log10;

    @Override
    public double calc(double x) {
        return x > 0 ? calcLogPart(x) : calcTrigPart(x);
    }

    private double calcTrigPart(double x) {
        return (Math.pow(cot.calc(x) / cot.calc(x), 3) + cos.calc(x)) * (sec.calc(x) + cot.calc(x));
    }

    private double calcLogPart(double x) {
        double row1 = Math.pow(ln.calc(x) + log10.calc(x), 2);
        double row2 = log3.calc(x);
        double row3 = log2.calc(x) - ln.calc(x);
        double row4 = ln.calc(x) - (log2.calc(x) - log2.calc(x));
        double denominator = Math.pow(log5.calc(x) - ln.calc(x), 2) - log10.calc(x) * (log10.calc(x) - log3.calc(x));

        double frac1 = row1 / row2;
        double frac2 = row3 / row4;
        double numerator = frac1 / frac2;

        return numerator / denominator;
    }
}
