package pd20.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import pd20.entity.Todo;

import java.util.List;

public class TodosService {
    ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public List<Todo> parseTodos(String userTodoResponse) throws JsonProcessingException {
        List<Todo> todoList = objectMapper.readValue(userTodoResponse, new TypeReference<List<Todo>>() {});
        return todoList.stream()
                .filter(todo -> !todo.isCompleted())
                .toList();
    }
}
