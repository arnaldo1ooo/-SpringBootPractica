package com.practica.springBootPractica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class SpringBootPracticaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootPracticaApplication.class, args);
	}

}
