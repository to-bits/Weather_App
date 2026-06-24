package com.WetherApp.demo.service;

import com.WetherApp.demo.model.WeatherResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${weather.api.key:}")
    private String apiKey;

    public WeatherResponse getWeather(String city) {
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&units=metric&appid=" + apiKey;
        RestTemplate restTemplate = new RestTemplate();

        try {
            String response = restTemplate.getForObject(url, String.class);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response);

            return new WeatherResponse(
                    root.path("name").asText(city),
                    root.path("main").path("temp").asDouble(0),
                    root.path("main").path("humidity").asInt(0),
                    root.path("weather").path(0).path("description").asText("No description available")
            );
        } catch (Exception e) {
            return new WeatherResponse(city, 0, 0, "Error / City not found");
        }
    }
}
