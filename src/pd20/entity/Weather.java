package pd20.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class Weather {
    @JsonProperty("current_weather")
    private Current_weather currentWeather;
}
