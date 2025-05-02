package ru.itmo.tpo.api;

import com.intuit.karate.junit5.Karate;

public abstract class BasicKarateTest {

    public Karate run() {
        return Karate.run(getFilePath(getFeatureName()));
    }

    private String getFilePath(String feature) {
        return "classpath:features/" + feature + ".feature";
    }

    private String getFeatureName() {
        String className = this.getClass().getSimpleName();
        if (className.endsWith("Test")) {
            className = className.substring(0, className.length() - "Test".length());
        }

        return className
                .replaceAll("([a-z])([A-Z])", "$1_$2")
                .toLowerCase();
    }
}
