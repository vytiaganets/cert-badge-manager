package com.example.solana.certbadgemanager.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.TransactionStatus;

@Service
public class TokenomicsTransactionService {

    private final TransactionManager transactionManager;
    private final TokenomicsService tokenomicsService;

    public TokenomicsTransactionService(TransactionManager transactionManager, TokenomicsService tokenomicsService) {
        this.transactionManager = transactionManager;
        this.tokenomicsService = tokenomicsService;
    }

    public void executeTransactionalOperation() {
        TransactionStatus status = transactionManager.beginTransaction();
        try {

            tokenomicsService.createOrUpdateTokenomicsData();


            transactionManager.commitTransaction(status);
        } catch (Exception e) {

            transactionManager.rollbackTransaction(status);
            throw e;
        }
    }
}
