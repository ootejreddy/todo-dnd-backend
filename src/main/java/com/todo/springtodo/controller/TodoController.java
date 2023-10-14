package com.todo.springtodo.controller;

import com.todo.springtodo.domain.TaskBoard;
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
    @PostMapping("/saveBoard")
    public ResponseEntity<?> save(@RequestBody TaskBoard taskBoard){
        System.out.println("The task is: "+taskBoard);
        System.out.println("task "+ taskBoard.getBoardName());
        return new ResponseEntity<>(taskService.create(taskBoard), HttpStatus.CREATED);
    }

    @CrossOrigin
    @GetMapping("/taskBoards")
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
    @PutMapping("/task/{boardId}")
    public ResponseEntity<?> update(@PathVariable Long boardId, @RequestBody Task task){
        System.out.println("The task name is: "+ task.getTaskName());
        System.out.println("The task id is: "+ task.getTaskId());
        return new ResponseEntity<>(taskService.update(boardId, task), HttpStatus.OK);
    }

//    @CrossOrigin
//    @PutMapping("/updateTask/{id}/")
    @CrossOrigin
    @DeleteMapping("/task/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        return new ResponseEntity<>(taskService.deleteTask(id), HttpStatus.OK);
    }
    @CrossOrigin
    @DeleteMapping("/taskBoard/{id}")
    public ResponseEntity<?> deleteByBoardId(@PathVariable Long id){
        return new ResponseEntity<>(taskService.delete(id), HttpStatus.OK);
    }
}
