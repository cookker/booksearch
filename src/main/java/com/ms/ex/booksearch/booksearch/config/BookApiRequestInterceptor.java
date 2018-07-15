package com.ms.ex.booksearch.booksearch.config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class BookApiRequestInterceptor implements ClientHttpRequestInterceptor {
    private final String HEADER_CLIENT = "Client";
    private final String clientId;

    public BookApiRequestInterceptor(String clientId) {
        this.clientId = clientId;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        HttpHeaders headers = request.getHeaders();
        headers.add(HEADER_CLIENT, clientId);
        if (request.getMethod() == HttpMethod.POST) {
            headers.setContentType(MediaType.APPLICATION_JSON);
        }
        return execution.execute(request, body);
    }
}
