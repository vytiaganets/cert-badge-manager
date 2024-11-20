package com.example.solana.certbadgemanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Service
public class TokenomicsTransactionService {

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired
    private TokenomicsService tokenomicsService;

    public void executeTransactionalOperation() {

        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setName("TokenomicsTransaction");
        def.setPropagationBehavior(DefaultTransactionDefinition.PROPAGATION_REQUIRED); // Транзакція буде використовувати існуючу, якщо вона є

        TransactionStatus status = transactionManager.getTransaction(def);
        try {

            tokenomicsService.createOrUpdateTokenomicsData();


            transactionManager.commit(status);
        } catch (Exception e) {

            transactionManager.rollback(status);
            throw e;
        }
    }
}
