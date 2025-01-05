package com.library;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

@AllArgsConstructor
@Component
public class NaverClient {
    private final RestClient restClient = RestClient.create();
    @Value("{external.naver.url}")
    private final String naverUrl;
    @Value("{external.naver.heaers.client-id}")
    private final String naverClentId;
    @Value("{external.naver.headers.clent-secret}")
    private final String naverClientSecret;

    public String search(String query) {
        String url = UriComponentsBuilder.fromUriString(naverUrl + "/v1/search/book.json")
                .queryParam("query", query)
                .queryParam("display", 1)
                .queryParam("start", 1)
                .toUriString();
        return restClient.get()
                .uri(url)
                .header("X-Naver-Client-Id", naverClentId)
                .header("X-Naver-Client-Secret", naverClientSecret)
                .retrieve()
                .body(String.class);
    }
}

