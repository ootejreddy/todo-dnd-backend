package com.todo.springtodo.service;

import com.todo.springtodo.domain.Task;
import com.todo.springtodo.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RequiredArgsConstructor
@Service
public class TaskService {
    private final TaskRepository taskRepository;
    @Transactional
    public Task create(Task task){
        return taskRepository.save(task);
    }

    @Transactional(readOnly = true)
    public List<Task> findAll(){
        return taskRepository.findAll();
    }

    @Transactional
    public Task update(Long id, Task task){
        Task taskEntity = taskRepository.findById(Math.toIntExact(id))
                .orElseThrow(()->new IllegalArgumentException("check Id"));  //Persistence Context

        taskEntity.setName(task.getName());
        taskEntity.setStatus(task.getStatus());
        return taskEntity;
    }
    @Transactional
    public String delete(Long id){
        taskRepository.deleteById(Math.toIntExact(id));
        return "Task Deleted";
    }
}
