package com.eatclub.takehome;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.json.simple.JSONObject;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import java.util.List;

@SpringBootApplication
public class TakehomeApplication {
	private static final Logger log = LoggerFactory.getLogger(TakehomeApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(TakehomeApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(RestaurantRepository restaurantRepository, DataLoader loader)  {
		return args -> {
			JSONObject jsonObject = loader.getData();
			List<JSONObject> restaurants = (List<JSONObject>) jsonObject.get("restaurants");
			for (JSONObject restaurantObj : restaurants) {
				Restaurant restaurant = RestaurantBuilder.build(restaurantObj);
				log.info("Load restaurant " + restaurant.objectId());
				restaurantRepository.add(restaurant);
			}
		};
	}

}
