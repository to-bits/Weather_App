package com.WetherApp.demo.controller;

import com.WetherApp.demo.model.WeatherResponse;
import com.WetherApp.demo.service.WeatherService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class weatherController {

    private final WeatherService weatherService;

    public weatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    public String home(@RequestParam(value = "city", defaultValue = "Dhaka") String city) {
        WeatherResponse weather = weatherService.getWeather(city);

        return """
                <!DOCTYPE html>
                <html lang=\"en\">
                <head>
                    <meta charset=\"UTF-8\" />
                    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />
                    <title>Weather Dashboard</title>
                    <style>
                        :root {
                            color-scheme: dark;
                            --bg: #050816;
                            --panel: rgba(15, 23, 42, 0.8);
                            --panel-2: rgba(22, 32, 54, 0.7);
                            --text: #f4f7ff;
                            --muted: #90a4c7;
                            --accent: #4fd1ff;
                            --accent-2: #7c7cff;
                            --accent-3: #ffb36b;
                            --shadow: 0 20px 45px rgba(0, 0, 0, 0.35);
                        }
                        * { box-sizing: border-box; }
                        body {
                            margin: 0;
                            font-family: Inter, 'Segoe UI', Roboto, sans-serif;
                            background: radial-gradient(circle at top left, #13223f 0%, var(--bg) 45%, #04050c 100%);
                            color: var(--text);
                            min-height: 100vh;
                            padding: 20px;
                        }
                        .app-shell {
                            max-width: 980px;
                            margin: 0 auto;
                            display: grid;
                            gap: 16px;
                        }
                        .glass-card {
                            background: linear-gradient(135deg, rgba(24, 34, 57, 0.92), rgba(10, 15, 29, 0.86));
                            border: 1px solid rgba(255,255,255,0.08);
                            box-shadow: var(--shadow);
                            backdrop-filter: blur(18px);
                            border-radius: 24px;
                        }
                        .hero { padding: 24px; }
                        .topbar { display: flex; justify-content: space-between; align-items: center; margin-bottom: 18px; }
                        .pill { display: inline-flex; align-items: center; gap: 8px; padding: 8px 12px; border-radius: 999px; background: rgba(255,255,255,0.06); color: var(--muted); font-size: 13px; }
                        .search { display: flex; align-items: center; gap: 10px; margin-top: 14px; padding: 12px 14px; border-radius: 999px; background: rgba(255,255,255,0.06); }
                        .search input { flex: 1; border: none; outline: none; background: transparent; color: var(--text); font-size: 15px; }
                        .main-grid { display: grid; grid-template-columns: 1.35fr 0.8fr; gap: 16px; }
                        .weather-main { padding: 24px; display: flex; flex-direction: column; gap: 12px; }
                        .temp { font-size: 52px; font-weight: 700; letter-spacing: -1px; }
                        .sub { color: var(--muted); font-size: 14px; }
                        .stats { display: grid; grid-template-columns: repeat(3, minmax(0,1fr)); gap: 10px; margin-top: 8px; }
                        .stat { padding: 12px; border-radius: 16px; background: rgba(255,255,255,0.05); }
                        .stat strong { display: block; font-size: 15px; }
                        .chip-row { display: flex; flex-wrap: wrap; gap: 8px; margin-top: 8px; }
                        .chip { padding: 8px 10px; border-radius: 999px; background: rgba(79, 209, 255, 0.12); color: #ccefff; font-size: 12px; }
                        .side-panel { padding: 20px; display: flex; flex-direction: column; gap: 12px; }
                        .mini-card { padding: 14px; border-radius: 16px; background: rgba(255,255,255,0.05); }
                        .forecast { padding: 20px; }
                        .forecast-list { display: grid; gap: 10px; margin-top: 12px; }
                        .forecast-item { display: flex; align-items: center; justify-content: space-between; padding: 12px 14px; border-radius: 14px; background: rgba(255,255,255,0.05); }
                        .muted { color: var(--muted); }
                        @media (max-width: 760px) {
                            body { padding: 12px; }
                            .main-grid { grid-template-columns: 1fr; }
                            .stats { grid-template-columns: 1fr; }
                        }
                    </style>
                </head>
                <body>
                    <div class=\"app-shell\">
                        <section class=\"glass-card hero\">
                            <div class=\"topbar\">
                                <div>
                                    <div class=\"pill\">📍 Current location · Dhaka</div>
                                    <h1 style=\"margin: 6px 0 2px; font-size: 28px;\">Weather Dashboard</h1>
                                    <div class=\"sub\">Premium dark UI with live-ready weather insights</div>
                                </div>
                                <div class=\"pill\">⚡ Change location</div>
                            </div>

                            <div class=\"search\">
                                <span>🔍</span>
                                <input value=\"" + city + "\" placeholder=\"Search city\" />
                            </div>

                            <div class=\"main-grid\" style=\"margin-top: 16px;\">
                                <div class=\"glass-card weather-main\">
                                    <div class=\"pill\">☀️ " + weather.getDescription() + "</div>
                                    <div class=\"temp\">" + weather.getTemperature() + "°C</div>
                                    <div class=\"sub\">Feels like 29°C · Updated just now</div>
                                    <div class=\"chip-row\">
                                        <span class=\"chip\">Humidity " + weather.getHumidity() + "%</span>
                                        <span class=\"chip\">Wind 14 km/h</span>
                                        <span class=\"chip\">Pressure 1012 hPa</span>
                                    </div>
                                    <div class=\"stats\">
                                        <div class=\"stat\"><strong>🌤️ Clear</strong><span class=\"sub\">Condition</span></div>
                                        <div class=\"stat\"><strong>💧 82%</strong><span class=\"sub\">Humidity</span></div>
                                        <div class=\"stat\"><strong>🌀 10 km/h</strong><span class=\"sub\">Wind</span></div>
                                    </div>
                                </div>
                                <div class=\"glass-card side-panel\">
                                    <div class=\"mini-card\"><strong>Weather alerts</strong><div class=\"sub\">No active alerts in your area.</div></div>
                                    <div class=\"mini-card\"><strong>Sunrise / Sunset</strong><div class=\"sub\">05:45 · 18:20</div></div>
                                    <div class=\"mini-card\"><strong>Air quality</strong><div class=\"sub\">AQI 64 · Moderate</div></div>
                                </div>
                            </div>
                        </section>

                        <section class=\"glass-card forecast\">
                            <h3 style=\"margin: 0;\">7-Day Outlook</h3>
                            <div class=\"forecast-list\">
                                <div class=\"forecast-item\"><span>Today</span><span>☀️ 31°C</span><span class=\"muted\">Clear</span></div>
                                <div class=\"forecast-item\"><span>Tomorrow</span><span>🌤️ 29°C</span><span class=\"muted\">Bright</span></div>
                                <div class=\"forecast-item\"><span>Wed</span><span>☁️ 27°C</span><span class=\"muted\">Clouds</span></div>
                                <div class=\"forecast-item\"><span>Thu</span><span>🌦️ 25°C</span><span class=\"muted\">Rain</span></div>
                                <div class=\"forecast-item\"><span>Fri</span><span>🌩️ 24°C</span><span class=\"muted\">Storm</span></div>
                                <div class=\"forecast-item\"><span>Sat</span><span>🌤️ 28°C</span><span class=\"muted\">Sunny</span></div>
                                <div class=\"forecast-item\"><span>Sun</span><span>☀️ 32°C</span><span class=\"muted\">Clear</span></div>
                            </div>
                        </section>
                    </div>
                </body>
                </html>
                """;
    }

    @GetMapping("/weather")
    public WeatherResponse getWeather(@RequestParam String city) {
        return weatherService.getWeather(city);
    }
}
