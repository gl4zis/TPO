package ru.itmo.tpo.wiremock.business;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.net.SocketTimeoutException;
import java.util.List;
import java.util.Map;

@Log4j2
@RequiredArgsConstructor
public class BusinessService {
    private static final String KEY = "data";
    private final RestClient storageClient;

    public boolean processData(String data) {
        try {
            storageClient.post()
                    .body(Map.of(KEY, data))
                    .contentType(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .toBodilessEntity();

            ResponseEntity<List<Object>> storage = storageClient.get()
                    .retrieve()
                    .toEntity(new ParameterizedTypeReference<>() {});

            storageClient.delete()
                    .uri("/clear")
                    .retrieve()
                    .toBodilessEntity();

            log.info("Successfully processed data={}", data);
            return containsData(data, storage.getBody());
        } catch (HttpClientErrorException e) {
            log.error("Storage client error", e);
            return false;
        } catch (HttpServerErrorException e) {
            log.warn("Server is unavailable", e);
            return false;
        } catch (RestClientException e) {
            if (e.getCause() instanceof SocketTimeoutException) {
                log.error("Server timeout", e);
            }
            return false;
        }
    }

    private boolean containsData(String data, List<Object> obj) {
        for (Object o : obj) {
            if ((o instanceof String s && s.equals(data)) ||
                (o instanceof Map m && m.containsKey(KEY))) {
                return true;
            }
        }
        return false;
    }
}
