package pd20.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import pd20.entity.Weather;

public class WeatherService {
    ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public Weather parseWeather(String weatherResponse) throws JsonProcessingException {
        Weather weather = objectMapper.readValue(weatherResponse, Weather.class);
        return weather;
    }
}
