package ru.itmo.tpo.wiremock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.assertj.core.api.Assertions;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.test.context.ActiveProfiles;
import ru.itmo.tpo.wiremock.business.BusinessConfig;
import ru.itmo.tpo.wiremock.business.BusinessService;

import java.time.Duration;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@ActiveProfiles("test")
@SpringBootTest(classes = BusinessConfig.class)
@ExtendWith(OutputCaptureExtension.class)
public class WireMockDemonstrationTest {
    private static WireMockServer wireMockServer;
    @Autowired
    private BusinessService businessService;

    @BeforeAll
    static void beforeAll() {
        wireMockServer = new WireMockServer(8089);
        wireMockServer.start();

        WireMock.configureFor("localhost", 8089);
    }

    @AfterAll
    static void afterAll() {
        wireMockServer.stop();
    }

    @Test
    void testSuccessfulProcessing(CapturedOutput output) {
        stubFor(post(urlEqualTo("/mock"))
                .willReturn(aResponse().withStatus(200).withBody("")));

        stubFor(get(urlEqualTo("/mock"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("[{\"data\":\"Test Data\"}]")));

        stubFor(delete(urlEqualTo("/mock/clear"))
                .willReturn(aResponse().withStatus(200).withBody("")));

        boolean result = businessService.processData("Test Data");

        Awaitility.await()
                .atMost(Duration.ofSeconds(1))
                .pollInterval(Duration.ofMillis(100))
                .untilAsserted(() -> Assertions.assertThat(output).contains("Successfully processed data=Test Data"));
        Assertions.assertThat(result).isTrue();
    }

    @Test
    void testServerUnavailable(CapturedOutput output) {
        stubFor(post(urlEqualTo("/mock"))
                .willReturn(aResponse().withStatus(500)));

        boolean result = businessService.processData("Test Data");

        Awaitility.await()
                .atMost(Duration.ofSeconds(1))
                .pollInterval(Duration.ofMillis(100))
                .untilAsserted(() -> Assertions.assertThat(output).contains("Server is unavailable"));
        Assertions.assertThat(result).isFalse();
    }

    @Test
    void testServerLongDelay(CapturedOutput output) {
        stubFor(post(urlEqualTo("/mock"))
                .willReturn(aResponse().withStatus(200).withFixedDelay(5000)));

        boolean result = businessService.processData("Test Data");

        Awaitility.await()
                .atMost(Duration.ofSeconds(1))
                .pollInterval(Duration.ofMillis(100))
                .untilAsserted(() -> Assertions.assertThat(output).contains("Server timeout"));
        Assertions.assertThat(result).isFalse();
    }
}
