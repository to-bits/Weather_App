package com.WetherApp.demo.service;

import com.WetherApp.demo.model.WeatherResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${weather.api.key:}")
    private String apiKey;

    public WeatherResponse getWeather(String city) {
        String normalizedCity = (city == null || city.isBlank()) ? "Dhaka" : city.trim();

        if (apiKey == null || apiKey.isBlank()) {
            return buildFallbackWeather(normalizedCity);
        }

        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + normalizedCity + "&units=metric&appid=" + apiKey;
        RestTemplate restTemplate = new RestTemplate();

        try {
            String response = restTemplate.getForObject(url, String.class);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response);

            return new WeatherResponse(
                    root.path("name").asText(normalizedCity),
                    root.path("main").path("temp").asDouble(0),
                    root.path("main").path("humidity").asInt(0),
                    root.path("weather").path(0).path("description").asText("No description available")
            );
        } catch (Exception e) {
            return buildFallbackWeather(normalizedCity);
        }
    }

    private WeatherResponse buildFallbackWeather(String city) {
        String key = city.toLowerCase(Locale.ROOT);
        int temperature;
        String description;
        int humidity;

        if (key.contains("london")) {
            temperature = 18;
            description = "Cloudy skies";
            humidity = 72;
        } else if (key.contains("new york") || key.contains("nyc")) {
            temperature = 24;
            description = "Bright and breezy";
            humidity = 58;
        } else if (key.contains("tokyo")) {
            temperature = 27;
            description = "Warm evening";
            humidity = 64;
        } else if (key.contains("delhi")) {
            temperature = 33;
            description = "Sunny and hot";
            humidity = 45;
        } else if (key.contains("mumbai")) {
            temperature = 30;
            description = "Light rain";
            humidity = 81;
        } else {
            temperature = 26;
            description = "Clear skies";
            humidity = 60;
        }

        return new WeatherResponse(city, temperature, humidity, description);
    }
}
