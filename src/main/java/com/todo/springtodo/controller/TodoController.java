package com.todo.springtodo.controller;

import com.todo.springtodo.repository.TaskRepository;
import com.todo.springtodo.domain.Task;
import com.todo.springtodo.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class TodoController {
    private final TaskService taskService;
    @CrossOrigin
    @PostMapping("/saveTodo")
    public ResponseEntity<?> save(@RequestBody Task task){
        System.out.println("The task is: "+task);
        System.out.println("task "+ task.getName());
        return new ResponseEntity<>(taskService.create(task), HttpStatus.CREATED);
    }

    @CrossOrigin
    @GetMapping("/tasks")
    public ResponseEntity<?> findAll(){
        return new ResponseEntity<>(taskService.findAll(), HttpStatus.OK);
    }

//    @CrossOrigin
//    @GetMapping("/task/{id}")
//    public ResponseEntity<?> findAll(@PathVariable Long id){
//
//        return new ResponseEntity<>(taskService.findBook(id), HttpStatus.OK);
//    }
    @CrossOrigin
    @PutMapping("/task/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Task task){
        return new ResponseEntity<>(taskService.update(id, task), HttpStatus.OK);
    }
    @CrossOrigin
    @DeleteMapping("/task/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        return new ResponseEntity<>(taskService.delete(id), HttpStatus.OK);
    }
}
