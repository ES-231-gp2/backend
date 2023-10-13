package com.ufcg.es.biblioconex.utils;

import org.springframework.web.client.RestTemplate;

public class HttpClient {

    public static String getHttpResponse(String url) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }
}
