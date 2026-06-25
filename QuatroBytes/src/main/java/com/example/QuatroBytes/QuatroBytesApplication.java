package com.example.QuatroBytes;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "QuatroBytes", description = "Documentação da API REST do Projeto QuatroBytes", version = "1.0"))
public class QuatroBytesApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuatroBytesApplication.class, args);
	}

}

