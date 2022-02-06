package com.br.testeJava.cauculo_frete;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CauculoFreteApplication {

	public static void main(String[] args) {
		SpringApplication.run(CauculoFreteApplication.class, args);
	}

}
