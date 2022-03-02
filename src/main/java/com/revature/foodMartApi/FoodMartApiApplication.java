package com.revature.foodMartApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class FoodMartApiApplication {

	@GetMapping("/test")
	public String testEndPoint() {
		return "Hey from the CI/CD pipeline! We did the thing!";
	}

	public static void main(String[] args) {
		SpringApplication.run(FoodMartApiApplication.class, args);
	}
}
