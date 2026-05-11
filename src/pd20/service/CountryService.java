package pd20.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import pd20.entity.Country;

import java.util.List;

public class CountryService {
    ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public Country parseCountry(String countryResponse) throws JsonProcessingException {

        List<Country> countries = objectMapper.readValue(countryResponse, new TypeReference<List<Country>>() {
        });

        return countries.get(0);
    }
}
