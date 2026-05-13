package pd20.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import pd20.entity.User;
import pd20.exception.ResponseEcxeption;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class UserClient {
    private final String JSONPLACEHOLDER = "https://jsonplaceholder.typicode.com/users/";
    private final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    private final HttpClient client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(5))
            .followRedirects(HttpClient.Redirect.NORMAL)
            .version(HttpClient.Version.HTTP_2)
            .build();
    private final TodosClient todosClient;
    private final PostsClient postsClient;

    public UserClient(TodosClient todosClient, PostsClient postsClient) {
        this.todosClient = todosClient;
        this.postsClient = postsClient;
    }

    public User getUser(Long userId) {
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

            User user = parseUser(userResponse.body());
            user.setTodos(todosClient.getTodos(userId));
            user.setPosts(postsClient.getPosts(userId));

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

    public User parseUser(String userResponse) throws JsonProcessingException {
        User user = objectMapper.readValue(userResponse, User.class);
        return user;
    }
}
