package ru.itmo.tpo.awaitility;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;

public class AsyncDataFetcher<T> {
    private final AtomicReference<CompletableFuture<T>> fetchingHolder = new AtomicReference<>();

    public CompletableFuture<T> getData(Callable<CompletableFuture<T>> asyncDataProducer) throws Exception {
        CompletableFuture<T> future = new CompletableFuture<>();
        boolean fetcherWasSet = fetchingHolder.compareAndSet(null, future);
        if (fetcherWasSet) {
            asyncDataProducer.call().whenComplete((result, throwable) -> completeFuture(result, throwable, future));
        }

        return fetchingHolder.get();
    }

    private void completeFuture(T result, Throwable throwable, CompletableFuture<T> future) {
        if (result != null) {
            future.complete(result);
        } else if (throwable != null) {
            future.completeExceptionally(throwable);
        }
    }
}
