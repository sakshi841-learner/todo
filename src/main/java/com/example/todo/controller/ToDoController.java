package com.example.todo.controller;


import com.example.todo.model.ToDo;
import com.example.todo.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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


    @PutMapping
    public ToDo updateTodo(@RequestParam Long id, @RequestBody ToDo updatedTodo) {
        Optional<ToDo> optionalToDo = toDoRepository.findById(id);
        if (optionalToDo.isPresent()) {
            ToDo existingToDo = optionalToDo.get();
            existingToDo.setTask(updatedTodo.getTask());

            existingToDo.setCompleted(updatedTodo.isCompleted());
            return toDoRepository.save(existingToDo);
        } else {
            throw new RuntimeException("ToDo not found with id " + id);
        }
    }

    @DeleteMapping
    public String deleteTodo(@RequestParam Long id) {
        if (toDoRepository.existsById(id)) {
            toDoRepository.deleteById(id);
            return "Deleted ToDo with id " + id;
        } else {
            return "ToDo with id " + id + " not found";
        }
    }
}
