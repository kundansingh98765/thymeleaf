package com.todo.assessment;

import com.todo.assessment.dto.Response;
import com.todo.assessment.dto.Todo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Service
@FeignClient(name = "todo-service", url = "${todo-service.url}")
public interface FeignServiceClient {
    @PostMapping("/todo/add")
    ResponseEntity<Response> createResource(@RequestBody Todo todo);

    @GetMapping("/todo/get/{id}")
    ResponseEntity<Response> getResource(@PathVariable Long id);

    @GetMapping("/todo/getAll")
    ResponseEntity<Response> getResourceByFlag(@RequestParam("isCompleted") Boolean isCompleted);

    @DeleteMapping("/todo/delete/{id}")
    ResponseEntity<Response> deleteResource(@PathVariable Long id);
}
