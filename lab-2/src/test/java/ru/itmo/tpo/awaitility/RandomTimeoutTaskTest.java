package ru.itmo.tpo.awaitility;

import org.assertj.core.api.Assertions;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.concurrent.ForkJoinPool;

public class RandomTimeoutTaskTest {
    private static final long MIN_TIMEOUT_MS = 500;
    private static final long MAX_TIMEOUT_MS = 5000;

    private RandomTimeoutTask randomTimeoutTask;

    @BeforeEach
    void setUp() {
        randomTimeoutTask = new RandomTimeoutTask(MIN_TIMEOUT_MS, MAX_TIMEOUT_MS);
    }

    @Test
    public void randomTimeoutTest() {
        var future = randomTimeoutTask.runAsync(ForkJoinPool.commonPool());

        Awaitility.await()
                .atLeast(Duration.ofMillis(MIN_TIMEOUT_MS))
                .atMost(Duration.ofMillis(MAX_TIMEOUT_MS))
                .pollInterval(Duration.ofMillis(100))
                .ignoreExceptions()
                .until(future::isDone);

        Assertions.assertThat(future.getNow(false)).isTrue();
    }
}
