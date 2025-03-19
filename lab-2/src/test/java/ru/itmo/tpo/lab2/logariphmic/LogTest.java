package ru.itmo.tpo.lab2.logariphmic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import ru.itmo.tpo.lab2.TestUtils;
import ru.itmo.tpo.lab2.math.logariphmic.Ln;
import ru.itmo.tpo.lab2.math.logariphmic.Log;

import java.util.stream.Stream;

import static ru.itmo.tpo.lab2.TestUtils.*;

public class LogTest {
    private Ln ln;
    private Log log2;
    private Log log3;
    private Log log5;
    private Log log10;

    @BeforeEach
    void setUp() {
        ln = Mockito.spy(new Ln());
        log2 = Mockito.spy(new Log(2, ln));
        log3 = Mockito.spy(new Log(3, ln));
        log5 = Mockito.spy(new Log(5, ln));
        log10 = Mockito.spy(new Log(10, ln));
        if (USE_MOCK_LN) Mockito.doAnswer(LN_ANSWER).when(ln).calc(Mockito.anyDouble());
        if (USE_MOCK_LOG) {
            Mockito.doAnswer(LOG_2_ANSWER).when(log2).calc(Mockito.anyDouble());
            Mockito.doAnswer(LOG_3_ANSWER).when(log3).calc(Mockito.anyDouble());
            Mockito.doAnswer(LOG_5_ANSWER).when(log5).calc(Mockito.anyDouble());
            Mockito.doAnswer(LOG_10_ANSWER).when(log10).calc(Mockito.anyDouble());
        }
    }

    public static Stream<Arguments> testTable2() {
        return TestUtils.buildTestArgs(LOG_2_MOCK_MAP);
    }

    public static Stream<Arguments> testTable3() {
        return TestUtils.buildTestArgs(LOG_3_MOCK_MAP);
    }

    public static Stream<Arguments> testTable5() {
        return TestUtils.buildTestArgs(LOG_5_MOCK_MAP);
    }

    public static Stream<Arguments> testTable10() {
        return TestUtils.buildTestArgs(LOG_10_MOCK_MAP);
    }

    @ParameterizedTest
    @MethodSource("testTable2")
    public void log2CalculationTest(double x, double expected) {
        Assertions.assertEquals(expected, log2.calc(x), EPS);
        Mockito.verify(ln, USE_MOCK_LOG ? Mockito.never() : Mockito.atLeastOnce()).calc(Mockito.anyDouble());
    }

    @ParameterizedTest
    @MethodSource("testTable3")
    public void log3CalculationTest(double x, double expected) {
        Assertions.assertEquals(expected, log3.calc(x), EPS);
        Mockito.verify(ln, USE_MOCK_LOG ? Mockito.never() : Mockito.atLeastOnce()).calc(Mockito.anyDouble());
    }

    @ParameterizedTest
    @MethodSource("testTable5")
    public void log5CalculationTest(double x, double expected) {
        Assertions.assertEquals(expected, log5.calc(x), EPS);
        Mockito.verify(ln, USE_MOCK_LOG ? Mockito.never() : Mockito.atLeastOnce()).calc(Mockito.anyDouble());
    }

    @ParameterizedTest
    @MethodSource("testTable10")
    public void log10CalculationTest(double x, double expected) {
        Assertions.assertEquals(expected, log10.calc(x), EPS);
        Mockito.verify(ln, USE_MOCK_LOG ? Mockito.never() : Mockito.atLeastOnce()).calc(Mockito.anyDouble());
    }
}
