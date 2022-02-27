package com.ista.isp.assessment.todo.services;

import com.ista.isp.assessment.todo.model.Todo;

import java.util.List;

public interface TodoService {
    List<Todo> getTodoList();

    Todo getTodo(int id);

    Todo storeTodo(Todo todo);

    boolean deleteTodo(int id);
}
