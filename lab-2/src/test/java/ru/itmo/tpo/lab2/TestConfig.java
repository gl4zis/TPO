package ru.itmo.tpo.lab2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.itmo.tpo.lab2.math.MathFunction;

import java.util.Map;

@Configuration
public class TestConfig {
    @Bean
    public MathFunction mockCos() {
        return new MockFunction(Map.of(
                0d, 1d,
                Math.PI / 2, 0d,
                Math.PI / 6, 0.86602,
                0.86, 0.65244,
                5d, 0.28366
        ));
    }

    @Bean
    public MathFunction mockSin() {
        return new MockFunction(Map.of(
                0d, 0d,
                Math.PI / 2, 1d,
                Math.PI / 6, 0.5,
                0.86, 0.75784,
                5d, -0.95892
        ));
    }
}
