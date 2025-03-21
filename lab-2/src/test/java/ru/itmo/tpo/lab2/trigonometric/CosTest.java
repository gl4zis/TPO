package ru.itmo.tpo.lab2.trigonometric;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import ru.itmo.tpo.lab2.TestUtils;
import ru.itmo.tpo.lab2.math.trigonometric.Cos;

import java.util.stream.Stream;

import static ru.itmo.tpo.lab2.TestUtils.*;

public class CosTest {
    private Cos cos;

    @BeforeEach
    void setUp() {
        cos = Mockito.spy(new Cos());
        if (USE_MOCK_COS) Mockito.doAnswer(COS_ANSWER).when(cos).calc(Mockito.anyDouble());
    }

    public static Stream<Arguments> testTable() {
        return TestUtils.buildTestArgs(COS_MOCK_MAP);
    }

    @ParameterizedTest
    @MethodSource("testTable")
    public void cosCalculationTest(double x, double expected) {
        Assertions.assertEquals(expected, cos.calc(x), EPS);
    }
}
