package ru.itmo.tpo.lab2.trigonometric;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.itmo.tpo.lab2.FuncCalculator;
import ru.itmo.tpo.lab2.TestConfig;
import ru.itmo.tpo.lab2.math.MathFunction;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.NONE,
        classes = {TestConfig.class, FuncCalculator.class}
)
public class SinTest {
    @Autowired
    private MathFunction cos;
    @Autowired @Spy
    private MathFunction mockCos;

    @Test
    public void test() {
        Assertions.assertEquals(1, mockCos.calc(0), 1e-6);
        Mockito.verify(mockCos, Mockito.times(1)).calc(Mockito.anyDouble());
    }
}
