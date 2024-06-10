package com.AlexandreLoiola.PaymentIntegrationSystem.service;

import com.AlexandreLoiola.PaymentIntegrationSystem.rest.form.BoletoPaymentForm;
import com.AlexandreLoiola.PaymentIntegrationSystem.rest.form.CardPaymentForm;
import com.AlexandreLoiola.PaymentIntegrationSystem.rest.form.PixPaymentForm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;

@Service
public class PagSeguroService {

    @Value("${pagseguro.api.url}")
    private String apiUrl;

    @Value("${pagseguro.api.token}")
    private String authorizationToken;

    private final RestTemplate restTemplate;

    public PagSeguroService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<String> createBoletoOrder(BoletoPaymentForm order) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(authorizationToken);

        HttpEntity<BoletoPaymentForm> request = new HttpEntity<>(order, headers);
        return restTemplate.exchange(apiUrl + "/orders", HttpMethod.POST, request, String.class);
    }

    public ResponseEntity<String> createPixOrder(PixPaymentForm order) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(authorizationToken);

        HttpEntity<PixPaymentForm> request = new HttpEntity<>(order, headers);
        return restTemplate.exchange(apiUrl + "/orders", HttpMethod.POST, request, String.class);
    }

    public ResponseEntity<String> createCardOrder(CardPaymentForm order) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(authorizationToken);

        HttpEntity<CardPaymentForm> request = new HttpEntity<>(order, headers);
        return restTemplate.exchange(apiUrl + "/orders", HttpMethod.POST, request, String.class);
    }

    public ResponseEntity<String> calculateFees() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("Authorization", "Bearer " + authorizationToken);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiUrl + "/charges/fees/calculate")
                .queryParam("payment_methods", "CREDIT_CARD")
                .queryParam("value", "10000")
                .queryParam("max_installments", "10")
                .queryParam("max_installments_no_interest", "4")
                .queryParam("credit_card_bin", "552100")
                .queryParam("show_seller_fees", "true");

        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                String.class
        );

        return response;
    }
}