package com.AlexandreLoiola.PaymentIntegrationSystem.service;


import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private PagSeguroService pagSeguroService;

    private TransactionService transactionService;

    public PaymentService(PagSeguroService pagSeguroService, TransactionService transactionService) {
        this.pagSeguroService = pagSeguroService;
        this.transactionService = transactionService;
    }
}