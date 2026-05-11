package pd20.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import pd20.entity.User;

public class UserService {
    ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public User parseUser(String userResponse) throws JsonProcessingException {
        User user = objectMapper.readValue(userResponse, User.class);
        return user;
    }
}
