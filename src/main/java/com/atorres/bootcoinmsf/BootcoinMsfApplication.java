package com.atorres.bootcoinmsf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactivefeign.spring.config.EnableReactiveFeignClients;

@EnableReactiveFeignClients
@SpringBootApplication
public class BootcoinMsfApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootcoinMsfApplication.class, args);
	}

}
