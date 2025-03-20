package ru.itmo.tpo.awaitility;

import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.Duration;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;

public class AsyncDataFetcherTest {
    private final AsyncDataFetcher<Boolean> asyncDataFetcher = Mockito.spy(new AsyncDataFetcher<>());
    
    @Test
    @SneakyThrows
    public void multithreadingTest() {
        Callable<CompletableFuture<Boolean>> dataProducerMock = Mockito.mock(Callable.class);
        Mockito.when(dataProducerMock.call()).thenReturn(CompletableFuture.supplyAsync(() -> {
            AwaitUtils.sleep(1000);
            return true;
        }));

        var future = asyncDataFetcher.getData(dataProducerMock);
        for (int i = 0; i < 10; i ++) {
            ForkJoinPool.commonPool().submit(() -> asyncDataFetcher.getData(dataProducerMock));
        }

        Awaitility.await()
                .atMost(Duration.ofMillis(5000))
                .pollInterval(Duration.ofMillis(100))
                .ignoreExceptions()
                .until(future::isDone);

        Assertions.assertThat(future.getNow(false)).isTrue();
        Mockito.verify(asyncDataFetcher, Mockito.atLeast(10)).getData(Mockito.any());
        Mockito.verify(dataProducerMock, Mockito.times(1)).call();
    }
}
