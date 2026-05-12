package pd20.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import pd20.entity.Todo;
import pd20.exception.ResponseEcxeption;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;

public class TodosClient {
    private final String JSONPLACEHOLDER = "https://jsonplaceholder.typicode.com/users/";
    private final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    private final HttpClient client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(5))
            .followRedirects(HttpClient.Redirect.NORMAL)
            .version(HttpClient.Version.HTTP_2)
            .build();

    public List<Todo> getTodos(Long userId) {
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
            return parseTodos(userTodosResponse.body());
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

    public List<Todo> parseTodos(String userTodoResponse) throws JsonProcessingException {
        List<Todo> todoList = objectMapper.readValue(userTodoResponse, new TypeReference<List<Todo>>() {
        });
        return todoList.stream()
                .filter(todo -> !todo.isCompleted())
                .toList();
    }
}
