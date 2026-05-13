package pd20.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import pd20.entity.Post;
import pd20.exception.ResponseEcxeption;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;

public class PostsClient {
    private final String JSONPLACEHOLDER = "https://jsonplaceholder.typicode.com/users/";
    private final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    private final HttpClient client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(5))
            .followRedirects(HttpClient.Redirect.NORMAL)
            .version(HttpClient.Version.HTTP_2)
            .build();

    public List<Post> getPosts(Long userId) {
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
            return parsePosts(userPostsResponse.body());
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

    public List<Post> parsePosts(String userPostsResponse) throws JsonProcessingException {
        List<Post> postList = objectMapper.readValue(userPostsResponse, new TypeReference<List<Post>>() {
        });
        return postList.stream()
                .limit(3)
                .toList();
    }
}
