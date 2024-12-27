package com.example.avywhale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.avywhale.repository")
@EntityScan(basePackages = "com.example.avywhale.model")
public class SolanaCertificateAndBadgeManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SolanaCertificateAndBadgeManagerApplication.class, args);
	}

}
