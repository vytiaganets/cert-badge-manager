package com.example.solana.certbadgemanager.blockchain;

import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Service
public class CustomTransactionManager {

    private final PlatformTransactionManager transactionManager;

    public CustomTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public TransactionStatus beginTransaction() {
        System.out.println("Starting transaction...");
        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        definition.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        definition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        return transactionManager.getTransaction(definition);
    }

    public void commitTransaction(TransactionStatus status) {
        if (!status.isCompleted()) {
            System.out.println("Committing transaction...");
            transactionManager.commit(status);
        }
    }

    public void rollbackTransaction(TransactionStatus status) {
        if (!status.isCompleted()) {
            System.out.println("Rolling back transaction...");
            transactionManager.rollback(status);
        }
    }

}
