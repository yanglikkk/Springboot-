package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import com.example.demo.interceptor.SessionInterceptor;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(sessionInterceptor()).addPathPatterns("/order/*");
	}
	@Bean
	public SessionInterceptor sessionInterceptor() {
		return new SessionInterceptor();
	}
}
