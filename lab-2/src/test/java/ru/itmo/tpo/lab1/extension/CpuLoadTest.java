package ru.itmo.tpo.lab1.extension;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@ExtendWith(CpuUsageExtension.class)
public class CpuLoadTest {

    @Test
    public void heavyTest() throws InterruptedException {
        int threads = 100;
        ExecutorService executor = Executors.newFixedThreadPool(threads);

        for (int i = 0; i < threads; i++) {
            executor.submit(() -> {
                long sum = 0;
                for (long j = 0; j < 1_000_000_000L; j++) {
                    sum += j % 7;
                }
                Thread.sleep(5000);
                return sum;
            });
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);
    }
}
