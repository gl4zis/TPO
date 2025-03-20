package ru.itmo.tpo.awaitility;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AwaitUtils {

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
