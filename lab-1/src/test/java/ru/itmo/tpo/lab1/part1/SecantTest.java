package ru.itmo.tpo.lab1.part1;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

@Log4j2
public class SecantTest {
    private static final double EPS = 1E-6;

    public static Stream<Arguments> getArguments() {
        double[] data = new double[] {
                0, 1E-5, -1E-5, 0.5, -0.5, -1, 1,
                1.35, 1.55, Math.PI / 2 - 1E-3,
                Math.PI / 2, -Math.PI / 2, 7 * Math.PI / 2,
                Math.PI, -173 * Math.PI,
                100003, -12345678, Integer.MAX_VALUE,
                Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY
        };
        return Arrays.stream(data).mapToObj(Arguments::of);
    }

    @ParameterizedTest(name = "Secant test. alpha={0} radians")
    @MethodSource("getArguments")
    public void calcTest(double argRadians) {
        Assertions.assertEquals(getExpectedValue(argRadians), Secant.calc(argRadians), EPS);
    }

    private double getExpectedValue(double argRadians) {
        var cos = Math.cos(argRadians);
        if (Math.abs(cos) < EPS) {
            return Double.POSITIVE_INFINITY;
        }
        return 1 / cos;
    }
}
