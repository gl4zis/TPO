package ru.itmo.tpo.wiremock;

import org.assertj.core.api.Assertions;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import ru.itmo.tpo.wiremock.business.BusinessConfig;
import ru.itmo.tpo.wiremock.business.BusinessService;
import ru.itmo.tpo.wiremock.storage.StorageApp;

import java.time.Duration;

@SpringBootTest(classes = {BusinessConfig.class, StorageApp.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ExtendWith(OutputCaptureExtension.class)
public class ServicesInteractionTest {
    @Autowired
    private BusinessService businessService;

    @Test
    void testSuccessfulProcessing(CapturedOutput output) {
        boolean result = businessService.processData("Test Data");

        Awaitility.await()
                .atMost(Duration.ofSeconds(1))
                .pollInterval(Duration.ofMillis(100))
                .untilAsserted(() -> Assertions.assertThat(output).contains("Successfully processed data=Test Data"));
        Assertions.assertThat(result).isTrue();
    }

}
