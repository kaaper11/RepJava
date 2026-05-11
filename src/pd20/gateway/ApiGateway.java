package pd20.gateway;

import com.fasterxml.jackson.core.JsonProcessingException;
import pd20.entity.*;
import pd20.exception.ResponseEcxeption;
import pd20.service.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;

public class ApiGateway {
    private final String JSONPLACEHOLDER = "https://jsonplaceholder.typicode.com/users/";
    private final String OPEN_METEO = "https://api.open-meteo.com/v1/forecast?latitude=";
    private final String REST_COUNTRIES = "https://restcountries.com/v3.1/name/";

    private final HttpClient client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(5))
            .followRedirects(HttpClient.Redirect.NORMAL)
            .version(HttpClient.Version.HTTP_2)
            .build();
    private final UserService userService;
    private final WeatherService weatherService;
    private final TodosService todosService;
    private final PostsService postService;
    private final CountryService countryService;

    public ApiGateway(UserService userService, WeatherService weatherService, TodosService todosService,
                      PostsService postService, CountryService countryService) {
        this.userService = userService;
        this.weatherService = weatherService;
        this.todosService = todosService;
        this.postService = postService;
        this.countryService = countryService;
    }

    public UserRaport getUserRaport(Long userId) {
        User user = getUser(userId);
        Weather weather = getWeather(user.getAddressLat(), user.getAddressLng());
        UserRaport userRaport = new UserRaport(user, weather);
        return userRaport;
    }

    private User getUser(Long userId) {
        try {
            HttpRequest userRequest = HttpRequest.newBuilder()
                    .uri(URI.create(JSONPLACEHOLDER + userId))
                    .header("Accept", "application/json")
                    .header("User-Agent", "JavaHttpClient")
                    .GET()
                    .build();
            HttpResponse<String> userResponse = client.send(userRequest, HttpResponse.BodyHandlers.ofString());

            if (userResponse.statusCode() != 200) {
                throw new ResponseEcxeption("user");
            }

            User user = userService.parseUser(userResponse.body());
            user.setTodos(getTodos(userId));
            user.setPosts(getPosts(userId));

            return user;
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

    private List<Todo> getTodos(Long userId) {
        try {
            HttpRequest userTodosRequest = HttpRequest.newBuilder()
                    .uri(URI.create(JSONPLACEHOLDER + userId + "/todos"))
                    .header("Accept", "application/json")
                    .header("User-Agent", "JavaHttpClient")
                    .GET()
                    .build();
            HttpResponse<String> userTodosResponse = client.send(userTodosRequest, HttpResponse.BodyHandlers.ofString());
            if (userTodosResponse.statusCode() != 200) {
                throw new ResponseEcxeption("todos");
            }
            return todosService.parseTodos(userTodosResponse.body());
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

    private List<Post> getPosts(Long userId) {
        try {
            HttpRequest userPostsRequest = HttpRequest.newBuilder()
                    .uri(URI.create(JSONPLACEHOLDER + userId + "/posts"))
                    .header("Accept", "application/json")
                    .header("User-Agent", "JavaHttpClient")
                    .GET()
                    .build();
            HttpResponse<String> userPostsResponse = client.send(userPostsRequest, HttpResponse.BodyHandlers.ofString());
            if (userPostsResponse.statusCode() != 200) {
                throw new ResponseEcxeption("posts");
            }
            return postService.parsePosts(userPostsResponse.body());
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


    private Weather getWeather(double latitude, double longitude) {
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
            return weatherService.parseWeather(weatherResponse.body());
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

    public Country getCountryInfo(String countryName) {
        try {
            HttpRequest countryRequest = HttpRequest.newBuilder()
                    .uri(URI.create(REST_COUNTRIES + countryName))
                    .header("Accept", "application/json")
                    .header("User-Agent", "JavaHttpClient")
                    .GET()
                    .build();
            HttpResponse<String> countryResponse = client.send(countryRequest, HttpResponse.BodyHandlers.ofString());
            if (countryResponse.statusCode() != 200) {
                throw new ResponseEcxeption("country");
            }
            return countryService.parseCountry(countryResponse.body());
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
}
