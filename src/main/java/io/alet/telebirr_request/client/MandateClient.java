package io.alet.telebirr_request.client;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.MediaType.TEXT_XML;

@Service
@RequiredArgsConstructor
@Slf4j
public class MandateClient {

    private final RestTemplate restTemplate;


    public void send(String xml) {
        HttpEntity<String> requestBody = new HttpEntity<>(xml, headers());

        try {
            ResponseEntity<String> response = restTemplate.postForEntity("http://172.17.91.7:8080/payment/services/APIRequestMgrService",
                    requestBody,
                    String.class);
            log.info("response::{}", response.getBody());
            log.info("getStatusCode::{}", response.getStatusCode());
        } catch (Exception e) {
            log.info("Error::{}", e.getMessage());
        }
    }

    public HttpHeaders headers() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(TEXT_XML);
        return headers;
    }

}
