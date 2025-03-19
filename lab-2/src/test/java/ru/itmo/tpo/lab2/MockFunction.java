package ru.itmo.tpo.lab2;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import ru.itmo.tpo.lab2.math.MathFunction;

import java.util.Map;

@Log4j2
@RequiredArgsConstructor
public class MockFunction implements MathFunction {
    private final Map<Double, Double> mockAnswers;

    @Override
    public double calc(double x) {
        log.info("Mock function was called for x={}", x);
        if (mockAnswers.containsKey(x)) {
            return mockAnswers.get(x);
        }
        throw new IllegalArgumentException("Not implemented");
    }
}
