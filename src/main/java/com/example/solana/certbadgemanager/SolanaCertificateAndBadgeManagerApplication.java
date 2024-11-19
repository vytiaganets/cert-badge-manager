package com.example.solana.certbadgemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.solana.certbadgemanager.repository")
@EntityScan(basePackages = "com.example.solana.certbadgemanager.model")
@ComponentScan(basePackages = {"com.example.solana.certbadgemanager"})
public class SolanaCertificateAndBadgeManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SolanaCertificateAndBadgeManagerApplication.class, args);
	}

}
