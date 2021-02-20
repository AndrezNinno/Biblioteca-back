package com.andrezninno.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class CodigoBibliotecaApplication {

	public static void main(String[] args) {
		//SpringApplication.run(CodigoBibliotecaApplication.class, args);
		new SpringApplicationBuilder(CodigoBibliotecaApplication.class)
		.registerShutdownHook(true)
		.run(args);
	}

}
