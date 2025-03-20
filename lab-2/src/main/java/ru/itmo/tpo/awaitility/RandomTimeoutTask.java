package ru.itmo.tpo.awaitility;

import lombok.RequiredArgsConstructor;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

@RequiredArgsConstructor
public class RandomTimeoutTask {
    private final Random random = new Random();
    private final long minTimeout;
    private final long maxTimeout;

    public CompletableFuture<Boolean> runAsync(ExecutorService executor) {
        return CompletableFuture.supplyAsync(this::run, executor);
    }

    public boolean run() {
        long delay = minTimeout + random.nextLong(maxTimeout - minTimeout);
        AwaitUtils.sleep(delay);
        return true;
    }
}
