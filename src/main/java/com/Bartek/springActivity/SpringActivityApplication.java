package com.Bartek.springActivity;

import com.Bartek.springActivity.rest.ApiCaller;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.HttpClientErrorException;

import java.util.*;

@SpringBootApplication
public class SpringActivityApplication {
	private final ApiCaller apiCaller = new ApiCaller();
	private final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		SpringApplication.run(SpringActivityApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ConfigurableApplicationContext context) {
		return runner -> {
			System.out.println("Please provide github username");
			String username = scanner.nextLine();
			List apiResponse = null;
			try {
				apiResponse = apiCaller.callGetRequest("users/"+username+"/events");
				System.out.println("Output: ");
				for (Object activity : apiResponse) {
					System.out.println(formatApiResponse((LinkedHashMap) activity));
				}
			} catch (HttpClientErrorException.NotFound e) {
				System.out.println("User with such a username has not been found!");
			}
			catch (Exception e) {
				System.out.println("Api failure!");
			}
			finally {
				context.close();
			}

		};
	}
	private String formatApiResponse(LinkedHashMap activity) {
		String type = activity.get("type").toString();
		String repoName = ((LinkedHashMap<?, ?>) activity.get("repo")).get("name").toString();
		String createdAt = activity.get("created_at").toString();
		return "- " + type + " in repository: " + repoName + " at: " + createdAt;
	}

}
