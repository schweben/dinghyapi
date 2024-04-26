package org.schweben.dinghyapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class DinghyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DinghyApiApplication.class, args);
	}
}
