package com.vanguard.rebalance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.vanguard.rebalance.services")
public class RebalanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RebalanceApplication.class, args);
	}

}
