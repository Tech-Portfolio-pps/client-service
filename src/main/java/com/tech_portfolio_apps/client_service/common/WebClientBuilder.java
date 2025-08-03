package com.tech_portfolio_apps.client_service.common;


import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

public class WebClientBuilder {
    private final WebClient webClient;

    public WebClientBuilder(String baseUrl, MultiValueMap<String, String> headers) {
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeaders(httpHeaders -> httpHeaders.addAll(headers))
                .build();
    }

    public WebClient getWebClient() {
        return this.webClient;
    }

}
