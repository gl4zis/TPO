package ru.itmo.tpo.lab2;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.params.provider.Arguments;
import org.mockito.stubbing.Answer;

import java.util.Map;
import java.util.stream.Stream;

@Log4j2
public class TestUtils {
    public static final double EPS = 1e-3;

    public static final boolean USE_MOCK_COS = false;
    public static final boolean USE_MOCK_COT = false;
    public static final boolean USE_MOCK_SEC = false;
    public static final boolean USE_MOCK_LN = false;
    public static final boolean USE_MOCK_LOG = false;
    public static final boolean USE_MOCK_SYSTEM = false;

    public static final Map<Double, Double> COS_MOCK_MAP = Map.of(
            -0.03, 0.9995500337,
            1.6007963268, -0.02999550021,
            -0.18, 0.9838436928,
            1.7507963268, -0.1790295734,
            -0.666239, 0.7861516451,
            2.2370353268, -0.6180336487,
            -1.5, 0.07073720167,
            3.07079632679, -0.9974949866,
            -1.6, -0.0291995223,
            3.1707963268, -0.999573603
    );

    public static final Map<Double, Double> COT_MOCK_MAP = Map.of(
            -0.03, -33.3233327333,
            -0.18, -5.49542555435,
            -0.666239, -1.27202078179,
            -1.5, -0.0709148443027,
            -1.6, 0.0292119781999
    );

    public static final Map<Double, Double> SEC_MOCK_MAP = Map.of(
            -0.03, 1.00045016881,
            -0.18, 1.01642161995,
            -0.666239, 1.27201921702,
            -1.5, 14.136832903,
            -1.6, -34.24713561
    );

    public static final Map<Double, Double> LN_MOCK_MAP = Map.of(
            0.1, -2.30258509299,
            0.6, -0.510825623766,
            0.8, -0.223143551314,
            1.1, 0.0953101798043,
            14.213, 2.65415703876,
            2d, 0.69314718056,
            3d, 1.09861228867,
            5d, 1.60943791243,
            10d, 2.30258509299
    );

    public static final Map<Double, Double> LOG_2_MOCK_MAP = Map.of(
            0.1, -3.32192809489,
            0.6, -0.736965594166,
            0.8, -0.321928094887,
            1.1, 0.13750352375,
            14.213, 3.82913919756
    );

    public static final Map<Double, Double> LOG_3_MOCK_MAP = Map.of(
            0.1, -2.09590327429,
            0.6, -0.464973520718,
            0.8, -0.203114013575,
            1.1, 0.0867550643548,
            14.213, 2.4159178503
    );

    public static final Map<Double, Double> LOG_5_MOCK_MAP = Map.of(
            0.1, -1.43067655807,
            0.6, -0.317393805514,
            0.8, -0.138646883853,
            1.1, 0.0592195443316,
            14.213, 1.64912048999
    );

    public static final Map<Double, Double> LOG_10_MOCK_MAP = Map.of(
            0.1, -1d,
            0.6, -0.221848749616,
            0.8, -0.0969100130081,
            1.1, 0.0413926851582,
            14.213, 1.15268575604
    );

    public static final Map<Double, Double> SYSTEM_MOCK_MAP = Map.of(
            -0.03, -64.63122,
            -0.18, -8.88564,
            -0.666239, 0d,
            -1.5, 15.0609,
            -1.6, -33.21878,
            0.1, -6.333225,
            0.6, -28.54751,
            0.8, -65.351648,
            1.1, 153.00358,
            14.213, 5.494321
    );

    public static final Answer<Double> COS_ANSWER = (invocation) -> mockCalc(invocation.getArgument(0), COS_MOCK_MAP);
    public static final Answer<Double> COT_ANSWER = (invocation) -> mockCalc(invocation.getArgument(0), COT_MOCK_MAP);
    public static final Answer<Double> SEC_ANSWER = (invocation) -> mockCalc(invocation.getArgument(0), SEC_MOCK_MAP);
    public static final Answer<Double> LN_ANSWER = (invocation) -> mockCalc(invocation.getArgument(0), LN_MOCK_MAP);
    public static final Answer<Double> LOG_2_ANSWER = (invocation) -> mockCalc(invocation.getArgument(0), LOG_2_MOCK_MAP);
    public static final Answer<Double> LOG_3_ANSWER = (invocation) -> mockCalc(invocation.getArgument(0), LOG_3_MOCK_MAP);
    public static final Answer<Double> LOG_5_ANSWER = (invocation) -> mockCalc(invocation.getArgument(0), LOG_5_MOCK_MAP);
    public static final Answer<Double> LOG_10_ANSWER = (invocation) -> mockCalc(invocation.getArgument(0), LOG_10_MOCK_MAP);
    public static final Answer<Double> SYSTEM_ANSWER = (invocation) -> mockCalc(invocation.getArgument(0), SYSTEM_MOCK_MAP);

    private TestUtils() { }

    public static Stream<Arguments> buildTestArgs(Map<Double, Double> argToResult) {
        return argToResult.entrySet().stream().map(entry -> Arguments.of(entry.getKey(), entry.getValue()));
    }

    public static double mockCalc(double x, Map<Double, Double> map) {
        log.info("Mock function was called for x={}", x);
        Double val = getWithEpsilon(map, x);
        if (val == null) {
            throw new IllegalArgumentException("Not implemented");
        }
        return val;
    }

    private static Double getWithEpsilon(Map<Double, Double> map, double key) {
        for (Map.Entry<Double, Double> entry : map.entrySet()) {
            if (Math.abs(entry.getKey() - key) < EPS) {
                return entry.getValue();
            }
        }
        return null;
    }
}
