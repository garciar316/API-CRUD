package com.crud.demo.controller;

import com.crud.demo.entity.Todo;
import com.crud.demo.service.ITodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping
public class TodoController {

    private final ITodoService todoService;

    @Autowired
    public TodoController(ITodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping(value = "api/todos")
    public ResponseEntity<Iterable<Todo>> list(){
        try {
            return ResponseEntity.ok(todoService.list());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping(value = "api/todo")
    public ResponseEntity<Todo> save(@RequestBody Todo todo){
        try {
            return ResponseEntity.ok(todoService.save(todo));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(value = "api/todo")
    public ResponseEntity<Todo> update(@RequestBody Todo todo){
        try {
            return ResponseEntity.ok(todoService.save(todo));
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(value = "api/{id}/todo")
    public ResponseEntity<String> delete(@PathVariable("id")Long id){
        try {
            todoService.delete(id);
            return ResponseEntity.ok("Se elimino con éxito el registro");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(value = "api/{id}/todo")
    public ResponseEntity<Todo> get(@PathVariable("id") Long id){
        try {
            return ResponseEntity.ok(todoService.get(id));
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }
}
