package com.opytha.droprofitacademy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:.env")
public class DroprofitAcademyApplication {

	public static void main(String[] args) {
		SpringApplication.run(DroprofitAcademyApplication.class, args);
	}

}
