package com.indoCoupon.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class IndoCoupon02Application{

	public static void main(String[] args) {
		SpringApplication.run(IndoCoupon02Application.class, args);
	}
	
}
