package pd20.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import pd20.entity.Country;
import pd20.exception.ResponseEcxeption;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;

public class CountryClient {
    private final String REST_COUNTRIES = "https://restcountries.com/v3.1/name/";
    private final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    private final HttpClient client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(5))
            .followRedirects(HttpClient.Redirect.NORMAL)
            .version(HttpClient.Version.HTTP_2)
            .build();

    public Country getCountry(String countryName) {
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
            return parseCountry(countryResponse.body());
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

    public Country parseCountry(String countryResponse) throws JsonProcessingException {
        List<Country> countries = objectMapper.readValue(countryResponse, new TypeReference<List<Country>>() {
        });

        return countries.get(0);
    }
}
