package com.todo.springtodo.controller;

import com.todo.springtodo.repository.TaskRepository;
import com.todo.springtodo.domain.TaskBoard;
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
    public ResponseEntity<?> save(@RequestBody TaskBoard taskBoard){
        System.out.println("The taskBoard is: "+taskBoard);
        System.out.println("taskBoard "+ taskBoard.getName());
        return new ResponseEntity<>(taskService.create(taskBoard), HttpStatus.CREATED);
    }

    @CrossOrigin
    @GetMapping("/taskBoards")
    public ResponseEntity<?> findAll(){
        return new ResponseEntity<>(taskService.findAll(), HttpStatus.OK);
    }

//    @CrossOrigin
//    @GetMapping("/taskBoard/{id}")
//    public ResponseEntity<?> findAll(@PathVariable Long id){
//
//        return new ResponseEntity<>(taskService.findBook(id), HttpStatus.OK);
//    }
    @CrossOrigin
    @PutMapping("/taskBoard/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody TaskBoard taskBoard){
        return new ResponseEntity<>(taskService.update(id, taskBoard), HttpStatus.OK);
    }
    @CrossOrigin
    @DeleteMapping("/taskBoard/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        return new ResponseEntity<>(taskService.delete(id), HttpStatus.OK);
    }
}
