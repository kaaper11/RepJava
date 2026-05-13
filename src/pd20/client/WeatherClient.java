package pd20.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import pd20.entity.Weather;
import pd20.exception.ResponseEcxeption;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class WeatherClient {
    private final String OPEN_METEO = "https://api.open-meteo.com/v1/forecast?latitude=";
    private final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    private final HttpClient client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(5))
            .followRedirects(HttpClient.Redirect.NORMAL)
            .version(HttpClient.Version.HTTP_2)
            .build();

    public Weather getWeather(double latitude, double longitude) {
        try {
            HttpRequest weatherRequest = HttpRequest.newBuilder()
                    .uri(URI.create(OPEN_METEO + latitude + "&longitude=" + longitude + "&current_weather=true"))
                    .header("Accept", "application/json")
                    .header("User-Agent", "JavaHttpClient")
                    .GET()
                    .build();
            HttpResponse<String> weatherResponse = client.send(weatherRequest, HttpResponse.BodyHandlers.ofString());
            if (weatherResponse.statusCode() != 200) {
                throw new ResponseEcxeption("weather");
            }
            return parseWeather(weatherResponse.body());
        } catch (JsonProcessingException e) {
            System.err.println(e);
            return null;
        } catch (IOException | InterruptedException e) {
            System.err.println(e);
            return null;
        } catch (ResponseEcxeption e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public Weather parseWeather(String weatherResponse) throws JsonProcessingException {
        Weather weather = objectMapper.readValue(weatherResponse, Weather.class);
        return weather;
    }
}
