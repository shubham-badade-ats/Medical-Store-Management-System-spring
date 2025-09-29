package com.example.medstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class MedstoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(MedstoreApplication.class, args);
    }
    
    
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(
                            "http://localhost:4200",
                            "http://172.29.192.1:4200",
                            "http://192.168.11.71:4200",
                            "http://localhost:8081",
                            "http://localhost:8080",
                            "http://10.16.178.80:8080",
                            "http://192.168.11.30:8080"
                        )
                        .allowedMethods("*") // Allow all HTTP methods (GET, POST, etc.)
                        .allowedHeaders("*") // Allow all headers
                        .allowCredentials(true); // Optional: only if you're using cookies or Authorization headers
            }
        };
    }


}
