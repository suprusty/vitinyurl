package com.virtualidentity.vitinyurl;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import com.virtualidentity.vitinyurl.utils.ShortenedURLStatisticsHandler;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Virtual Identity API", version = "1.0", description = "Virtual Identity shorten URLs."))
public class TinyURLBootupApplication {

	public static void main(String[] args) {
		SpringApplication.run(TinyURLBootupApplication.class, args);
	}

	@Bean
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	ShortenedURLStatisticsHandler getShortenedURLStatisticsHandler() {
		return new ShortenedURLStatisticsHandler();
	}
}
