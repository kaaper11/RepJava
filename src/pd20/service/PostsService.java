package pd20.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import pd20.entity.Post;

import java.util.List;

public class PostsService {
    ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public List<Post> parsePosts(String userPostsResponse) throws JsonProcessingException {
        List<Post> postList = objectMapper.readValue(userPostsResponse, new TypeReference<List<Post>>() {});
        return postList.stream()
                .limit(3)
                .toList();
    }
}
