package com.crud.demo.service;

import com.crud.demo.entity.Todo;
import com.crud.demo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class TodoServiceImpl implements ITodoService {

    private final TodoRepository todoRepository;

    @Autowired
    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public Iterable<Todo> list(){
        return todoRepository.findAll();
    }

    @Override
    public Todo save(Todo todo){
        return todoRepository.save(todo);
    }

    @Override
    public Todo update(Todo todo){
        if (todoRepository.existsById(todo.getId())) {
            return save(todo);
        }
        throw new NoSuchElementException();
    }

    @Override
    public void delete(Long id){
        todoRepository.delete(get(id));
    }

    @Override
    public Todo get(Long id){
        return todoRepository.findById(id).orElseThrow();
    }
}
