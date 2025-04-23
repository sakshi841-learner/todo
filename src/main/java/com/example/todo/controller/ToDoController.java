package com.example.todo.controller;


import com.example.todo.model.ToDo;
import com.example.todo.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class ToDoController {

    @Autowired
    private ToDoRepository toDoRepository;

    @GetMapping
    public List<ToDo> getAllTodos() {
        return toDoRepository.findAll();
    }

    @PostMapping
    public ToDo createTodo(@RequestBody ToDo todo) {
        return toDoRepository.save(todo);
    }
}
