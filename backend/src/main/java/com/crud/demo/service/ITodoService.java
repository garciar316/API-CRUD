package com.crud.demo.service;

import com.crud.demo.entity.Todo;

public interface ITodoService {

    Iterable<Todo> list();

    Todo save(Todo todo);

    void delete(Long id);

    Todo get(Long id);
}
