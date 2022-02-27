package com.ista.isp.assessment.todo.controller;

import com.ista.isp.assessment.todo.model.Todo;
import com.ista.isp.assessment.todo.model.TodoRepository;
import com.ista.isp.assessment.todo.model.User;
import com.ista.isp.assessment.todo.model.UserRepository;
import com.ista.isp.assessment.todo.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodolistappApplication {
    private final TodoService todoService;

    @Autowired
    public TodolistappApplication(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping()
    public List<Todo> getTodoList() {
        return todoService.getTodoList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodo(@PathVariable("id") int id) {
        Todo todo = todoService.getTodo(id);
        return todo != null ? new ResponseEntity<>(todo, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    public ResponseEntity<String> storeTodo(@RequestBody Todo todo) {
        return todoService.storeTodo(todo) != null ? new ResponseEntity<>("Guardado con exito", HttpStatus.OK)
                : new ResponseEntity<>("Error al guardar", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> storeTodo(@PathVariable("id") int id, @RequestBody Todo todo) {
        Todo exTodo = todoService.getTodo(id);
        todo.setId(id);
        return exTodo != null ? todoService.storeTodo(todo) != null
                ? new ResponseEntity<>("Datos actualizados con éxito", HttpStatus.OK)
                : new ResponseEntity<>("Error al actualizar los datos", HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") int id) {
        Todo exTodo = todoService.getTodo(id);
        return exTodo != null ? todoService.deleteTodo(id)
                ? new ResponseEntity<>("Borrado con éxito", HttpStatus.OK)
                : new ResponseEntity<>("Error en el borrado", HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
