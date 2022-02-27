package com.ista.isp.assessment.todo.services;

import com.ista.isp.assessment.todo.model.Todo;
import com.ista.isp.assessment.todo.model.TodoRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TodoServiceImpl implements TodoService{
    private final TodoRepository todoRepository;

    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public List<Todo> getTodoList() {
        return (List<Todo>) todoRepository.findAll();
    }

    @Override
    public Todo getTodo(int id) {
        Optional<Todo> todo = todoRepository.findById(id);
        return todo.orElse(null);
    }

    @Override
    public Todo storeTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    @Override
    public boolean deleteTodo(int id) {
        boolean deleted = false;
        Optional<Todo> todo = todoRepository.findById(id);

        if (todo.isPresent()) {
            todoRepository.delete(todo.get());
            deleted = true;
        }
        return deleted;
    }
}
