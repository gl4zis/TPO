package ru.itmo.tpo.lab2.logariphmic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import ru.itmo.tpo.lab2.TestUtils;
import ru.itmo.tpo.lab2.math.logariphmic.Ln;

import java.util.stream.Stream;

import static ru.itmo.tpo.lab2.TestUtils.*;

public class LnTest {
    private Ln ln;

    @BeforeEach
    void setUp() {
        ln = Mockito.spy(new Ln());
        if (USE_MOCK_LN) Mockito.doAnswer(LN_ANSWER).when(ln).calc(Mockito.anyDouble());
    }

    public static Stream<Arguments> testTable() {
        return TestUtils.buildTestArgs(LN_MOCK_MAP);
    }

    @ParameterizedTest
    @MethodSource("testTable")
    public void lnCalculationTest(double x, double expected) {
        Assertions.assertEquals(expected, ln.calc(x), EPS);
    }
}
