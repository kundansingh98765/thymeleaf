package com.todo.assessment.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todo.assessment.FeignServiceClient;
import com.todo.assessment.dto.Todo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class TodoService {

    private final FeignServiceClient feignServiceClient;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    public TodoService(FeignServiceClient feignServiceClient) {
        this.feignServiceClient = feignServiceClient;
    }

    public List<Todo> getTodoByFlag(Boolean isCompleted) {
        List<Todo> list = new ArrayList<>();
        List<LinkedHashMap> restResponse = (List<LinkedHashMap>) feignServiceClient.getResourceByFlag(isCompleted).getBody().getData();
        restResponse.stream().forEach(longTodoLinkedHashMap -> {
            Todo todo = objectMapper.convertValue(longTodoLinkedHashMap, Todo.class);
            list.add(todo);
        });
        return list;
    }

    public Todo addTodo(Todo todo) {
        return objectMapper.convertValue(feignServiceClient.createResource(todo).getBody().getData(), Todo.class);
    }

    public void deleteTodo(Long id) {
        log.info("Deleting Todo for Id:{}", id);
        feignServiceClient.deleteResource(id);
    }
}
