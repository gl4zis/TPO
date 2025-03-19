package ru.itmo.tpo.lab2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import ru.itmo.tpo.lab2.math.FuncSystem;
import ru.itmo.tpo.lab2.math.logariphmic.Ln;
import ru.itmo.tpo.lab2.math.logariphmic.Log;
import ru.itmo.tpo.lab2.math.trigonometric.Cos;
import ru.itmo.tpo.lab2.math.trigonometric.Cot;
import ru.itmo.tpo.lab2.math.trigonometric.Sec;

import java.util.stream.Stream;

import static ru.itmo.tpo.lab2.TestUtils.*;
import static ru.itmo.tpo.lab2.TestUtils.COT_ANSWER;

public class FuncSystemTest {
    private FuncSystem funcSystem;

    @BeforeEach
    void setUp() {
        Cos cos = Mockito.spy(new Cos());
        Cot cot = Mockito.spy(new Cot(cos));
        Sec sec = Mockito.spy(new Sec(cos));
        Ln ln = Mockito.spy(new Ln());
        Log log2 = Mockito.spy(new Log(2, ln));
        Log log3 = Mockito.spy(new Log(3, ln));
        Log log5 = Mockito.spy(new Log(5, ln));
        Log log10 = Mockito.spy(new Log(10, ln));
        funcSystem = Mockito.spy(new FuncSystem(
                cos, cot, sec, ln, log2, log3, log5, log10
        ));
        if (USE_MOCK_COS) Mockito.doAnswer(COS_ANSWER).when(cos).calc(Mockito.anyDouble());
        if (USE_MOCK_COT) Mockito.doAnswer(COT_ANSWER).when(cot).calc(Mockito.anyDouble());
        if (USE_MOCK_SEC) Mockito.doAnswer(SEC_ANSWER).when(sec).calc(Mockito.anyDouble());
        if (USE_MOCK_LN) Mockito.doAnswer(LN_ANSWER).when(ln).calc(Mockito.anyDouble());
        if (USE_MOCK_LOG) {
            Mockito.doAnswer(LOG_2_ANSWER).when(log2).calc(Mockito.anyDouble());
            Mockito.doAnswer(LOG_3_ANSWER).when(log3).calc(Mockito.anyDouble());
            Mockito.doAnswer(LOG_5_ANSWER).when(log5).calc(Mockito.anyDouble());
            Mockito.doAnswer(LOG_10_ANSWER).when(log10).calc(Mockito.anyDouble());
        }
        if (USE_MOCK_SYSTEM) Mockito.doAnswer(SYSTEM_ANSWER).when(funcSystem).calc(Mockito.anyDouble());
    }

    public static Stream<Arguments> testTable() {
        return buildTestArgs(SYSTEM_MOCK_MAP);
    }

    @ParameterizedTest
    @MethodSource("testTable")
    public void systemCalculationTest(double x, double expected) {
        Assertions.assertEquals(expected, funcSystem.calc(x), EPS);
    }}
