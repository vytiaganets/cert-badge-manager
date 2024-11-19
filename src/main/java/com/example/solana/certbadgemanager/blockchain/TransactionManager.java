package com.example.solana.certbadgemanager.blockchain;

import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Service
public class TransactionManager {

    private final PlatformTransactionManager transactionManager;

    public TransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public TransactionStatus beginTransaction() {
        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        definition.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        definition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        return transactionManager.getTransaction(definition);
    }

    public void commitTransaction(TransactionStatus status) {
        if (!status.isCompleted()) {
            transactionManager.commit(status);
        }
    }

    public void rollbackTransaction(TransactionStatus status) {
        if (!status.isCompleted()) {
            transactionManager.rollback(status);
        }
    }
}
