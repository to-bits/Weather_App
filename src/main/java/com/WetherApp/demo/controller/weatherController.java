package com.WetherApp.demo.controller;

import com.WetherApp.demo.model.WeatherResponse;
import com.WetherApp.demo.service.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class weatherController {

    private final WeatherService weatherService;

    public weatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/")
    public String home() {
        return "Weather App Running! Use /weather?city=Dhaka";
    }

    @GetMapping("/weather")
    public WeatherResponse getWeather(@RequestParam String city) {
        return weatherService.getWeather(city);
    }
}
