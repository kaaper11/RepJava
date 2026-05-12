package pd20.gateway;

import pd20.entity.*;
import pd20.client.*;
import pd20.client.WeatherClient;

import java.net.http.HttpClient;
import java.time.Duration;

public class ApiGateway {
    private final HttpClient client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(5))
            .followRedirects(HttpClient.Redirect.NORMAL)
            .version(HttpClient.Version.HTTP_2)
            .build();
    private final UserClient userClient;
    private final WeatherClient weatherClient;
    private final CountryClient countryClient;

    public ApiGateway(UserClient userClient, WeatherClient weatherClient, CountryClient countryClient) {
        this.userClient = userClient;
        this.weatherClient = weatherClient;
        this.countryClient = countryClient;
    }

    public UserRaport getUserRaport(Long userId) {
        User user = getUser(userId);
        Weather weather = getWeather(user.getAddressLat(), user.getAddressLng());
        UserRaport userRaport = new UserRaport(user, weather);
        return userRaport;
    }

    private User getUser(Long userId) {
        return userClient.getUser(userId);
    }

    private Weather getWeather(double latitude, double longitude) {
        return weatherClient.getWeather(latitude, longitude);
    }

    public Country getCountryInfo(String countryName) {
        return countryClient.getCountry(countryName);
    }
}
