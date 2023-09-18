package com.todo.assessment.controller;

import com.todo.assessment.dto.Todo;
import com.todo.assessment.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TodoController {

    @Autowired
    TodoService todoService;

    @GetMapping("/")
    public String listTodos(Model model) {
        List<Todo> incompleteTodos = todoService.getTodoByFlag(Boolean.FALSE);
        List<Todo> completeTodos = todoService.getTodoByFlag(Boolean.TRUE);

        model.addAttribute("incompleteTodos", incompleteTodos);
        model.addAttribute("completeTodos", completeTodos);

        return "todo-list";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Todo todo, Model model) {
        model.addAttribute("todo", todoService.addTodo(todo));
        return "todo-details";
    }

    @GetMapping("/deleteById/{id}")
    public String deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return "redirect:/";
    }
}
