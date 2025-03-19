package ru.itmo.tpo.lab2.trigonometric;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.itmo.tpo.lab2.FuncCalculator;
import ru.itmo.tpo.lab2.TestConfig;
import ru.itmo.tpo.lab2.math.MathFunction;
import ru.itmo.tpo.lab2.math.trigonometric.Cos;

import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.NONE,
        classes = {TestConfig.class, FuncCalculator.class}
)
public class CosTest {
    private static final double EPS = 1e-5;

    @Autowired
    private Cos cos;
    @Autowired @Spy
    private MathFunction mockCos;

    public static Stream<Arguments> testTable() {
        return Stream.of(
                Arguments.of(0, 1),
                Arguments.of(Math.PI / 6, 0.86602),
                Arguments.of(Math.PI / 2, 0),
                Arguments.of(0.86, 0.65244)
        );
    }

    @ParameterizedTest
    @MethodSource("testTable")
    public void cosCalculationTest(double x, double expected) {
        Assertions.assertEquals(expected, cos.calc(x), EPS);
    }

    @ParameterizedTest
    @MethodSource("testTable")
    public void mockCosCalculationTest(double x, double expected) {
        Assertions.assertEquals(expected, mockCos.calc(x), EPS);
    }
}
