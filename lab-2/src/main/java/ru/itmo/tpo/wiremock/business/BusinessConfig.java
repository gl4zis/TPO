package ru.itmo.tpo.wiremock.business;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Log4j2
@Configuration
public class BusinessConfig {
    @Bean
    public RestClient storageClient(
            @Value("${storage.client.url}") String storageClientUrl
    ) {
        var reqFactory = new SimpleClientHttpRequestFactory();
        reqFactory.setConnectTimeout(300);
        reqFactory.setReadTimeout(500);

        return RestClient.builder()
                .requestFactory(reqFactory)
                .baseUrl(storageClientUrl)
                .build();
    }

    @Bean
    public BusinessService businessService(RestClient storageClient) {
        return new BusinessService(storageClient);
    }
}
