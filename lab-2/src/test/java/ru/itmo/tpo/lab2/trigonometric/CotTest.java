package ru.itmo.tpo.lab2.trigonometric;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import ru.itmo.tpo.lab2.math.trigonometric.Cos;
import ru.itmo.tpo.lab2.math.trigonometric.Cot;

import java.util.stream.Stream;

import static ru.itmo.tpo.lab2.TestUtils.*;

public class CotTest {
    private Cos cos;
    private Cot cot;

    @BeforeEach
    void setUp() {
        cos = Mockito.spy(new Cos());
        cot = Mockito.spy(new Cot(cos));
        if (USE_MOCK_COS) Mockito.doAnswer(COS_ANSWER).when(cos).calc(Mockito.anyDouble());
        if (USE_MOCK_COT) Mockito.doAnswer(COT_ANSWER).when(cot).calc(Mockito.anyDouble());
    }

    public static Stream<Arguments> testTable() {
        return buildTestArgs(COT_MOCK_MAP);
    }

    @ParameterizedTest
    @MethodSource("testTable")
    public void sinCalculationTest(double x, double expected) {
        Assertions.assertEquals(expected, cot.calc(x), EPS);
        Mockito.verify(cos, USE_MOCK_COT ? Mockito.never() : Mockito.atLeastOnce()).calc(Mockito.anyDouble());
    }
}
