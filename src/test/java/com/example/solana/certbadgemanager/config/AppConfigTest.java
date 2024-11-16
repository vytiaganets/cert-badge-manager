package com.example.solana.certbadgemanager.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class AppConfigTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void applicationContext_ShouldLoadAllBeans() {
        assertNotNull(applicationContext.getBean("solanaBlockchainClient"), "SolanaBlockchainClient bean should be loaded");
        assertNotNull(applicationContext.getBean("someOtherBean"), "SomeOtherBean should be loaded"); // Додайте інші бінари для перевірки, якщо потрібно
    }
}
