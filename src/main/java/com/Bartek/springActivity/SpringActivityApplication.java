package com.Bartek.springActivity;

import com.Bartek.springActivity.rest.ApiCaller;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringActivityApplication {
	private final ApiCaller apiCaller = new ApiCaller();

	public static void main(String[] args) {
		SpringApplication.run(SpringActivityApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return runner -> {
			System.out.println(apiCaller.callGetRequest("users/bkr4wczynski"));
		};
	}

}
