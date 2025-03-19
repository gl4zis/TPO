package ru.itmo.tpo.lab2.trigonometric;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import ru.itmo.tpo.lab2.TestUtils;
import ru.itmo.tpo.lab2.math.trigonometric.Cos;
import ru.itmo.tpo.lab2.math.trigonometric.Sec;

import java.util.stream.Stream;

import static ru.itmo.tpo.lab2.TestUtils.*;

public class SecTest {
    private Cos cos;
    private Sec sec;

    @BeforeEach
    void setUp() {
        cos = Mockito.spy(new Cos());
        sec = Mockito.spy(new Sec(cos));
        if (USE_MOCK_COS) Mockito.doAnswer(COS_ANSWER).when(cos).calc(Mockito.anyDouble());
        if (USE_MOCK_SEC) Mockito.doAnswer(SEC_ANSWER).when(sec).calc(Mockito.anyDouble());
    }

    public static Stream<Arguments> testTable() {
        return TestUtils.buildTestArgs(TestUtils.SEC_MOCK_MAP);
    }

    @ParameterizedTest
    @MethodSource("testTable")
    public void sinCalculationTest(double x, double expected) {
        Assertions.assertEquals(expected, sec.calc(x), EPS);
        Mockito.verify(cos, USE_MOCK_SEC ? Mockito.never() : Mockito.atLeastOnce()).calc(Mockito.anyDouble());
    }
}
