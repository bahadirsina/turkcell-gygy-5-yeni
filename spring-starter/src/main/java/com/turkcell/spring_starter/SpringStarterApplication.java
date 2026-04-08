package com.turkcell.spring_starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //Anototation => Sınıfın bir Spring Boot uygulaması olduğunu belirtir.
public class SpringStarterApplication {

	//Entrypoint
	public static void main(String[] args) {
		SpringApplication.run(SpringStarterApplication.class, args);
	}

}
