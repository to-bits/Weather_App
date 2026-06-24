# Weather App

A modern Spring Boot weather application with a polished dark-mode dashboard UI. The app displays a responsive weather experience with a premium glassmorphism style and a clean, futuristic design.

## Features

- Dark, mobile-first weather dashboard UI
- Current weather summary with temperature and description
- Humidity, wind, and pressure details
- Weather alerts, sunrise/sunset, and AQI cards
- 7-day forecast preview
- Spring Boot backend with REST endpoint support

## Tech Stack

- Java 21
- Spring Boot 3.5.3
- Gradle
- JUnit 5

## Project Structure

- `src/main/java` - Application source code
- `src/main/resources` - Configuration files
- `src/test/java` - Test classes

## Run the Application

From the project root, run:

```bash
./gradlew.bat bootRun
```

Then open:

```text
http://localhost:8080/
```

## Run Tests

```bash
./gradlew.bat test
```

## Notes

The app includes a polished UI experience out of the box. If you add a real OpenWeather API key, the app can fetch live weather data for different cities.
