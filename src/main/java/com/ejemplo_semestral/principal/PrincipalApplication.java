package com.ejemplo_semestral.principal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class PrincipalApplication {
    public static void main(String[] args) {

        Dotenv dotenv = Dotenv.load();
        dotenv.entries().forEach(entry ->
            System.setProperty(entry.getKey(), entry.getValue())
        );
        SpringApplication.run(PrincipalApplication.class, args);
    }
}

