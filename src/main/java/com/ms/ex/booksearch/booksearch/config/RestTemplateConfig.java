package com.ms.ex.booksearch.booksearch.config;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Value("${book.api.httpclient.connectionTimeout:15000}")
    private int timeout;
    @Value("${book.api.httpclient.connectionRequestTimeout:15000}")
    private int requestTimeout;
    @Value("${book.api.httpclient.socketTimeout:15000}")
    private int socketTimeout;
    @Value("${book.api.httpclient.pooling.maxTotal}")
    public int maxTotalConnectionsSize;
    @Value("${book.api.httpclient.pooling.maxPerRoute}")
    public int maxPerHost;

    @Bean
    public RestTemplate restTemplate() {
        return createTimeoutRestTemplate(timeout, requestTimeout, socketTimeout);
    }

    private RestTemplate getRestTemplate(HttpComponentsClientHttpRequestFactory factory,
                                         int connectTimeout,
                                         int connectionRequestTimeout,
                                         int readTimeout) {
        factory.setConnectTimeout(connectTimeout);
        factory.setConnectionRequestTimeout(connectionRequestTimeout); //connection manager
        factory.setReadTimeout(readTimeout);
        return new RestTemplate(factory);
    }


    private RestTemplate createTimeoutRestTemplate(int connectTimeout, int connectionRequestTimeout, int readTimeout) {
        CloseableHttpClient httpClient = getCloseableHttpClient();
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
        return getRestTemplate(factory, connectTimeout, connectionRequestTimeout, readTimeout);
    }

    private CloseableHttpClient getCloseableHttpClient() {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(maxTotalConnectionsSize);
        connectionManager.setDefaultMaxPerRoute(maxPerHost);

        return HttpClients
                .custom()
                .setConnectionManager(connectionManager)
                .build();
    }
}
