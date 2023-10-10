package com.todo.springtodo.service;

import com.todo.springtodo.domain.TaskBoard;
import com.todo.springtodo.repository.TaskBoardRepository;
import com.todo.springtodo.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RequiredArgsConstructor
@Service
public class TaskService {
    @Autowired
    private final TaskRepository taskRepository;
    @Autowired
    private final TaskBoardRepository taskBoardRepository;
    @Transactional
    public TaskBoard create(TaskBoard taskBoard){
        return taskBoardRepository.save(taskBoard);
    }

    @Transactional(readOnly = true)
    public List<TaskBoard> findAll(){
        return taskBoardRepository.findAll();
    }

    @Transactional
    public TaskBoard update(Long id, TaskBoard taskBoard){
        TaskBoard taskEntity = taskBoardRepository.findById((long) Math.toIntExact(id))
                .orElseThrow(()->new IllegalArgumentException("check Id"));  //Persistence Context
//        System.out.println(taskEntity.getData());
        System.out.println("reached Update function");
        taskEntity.setName(taskBoard.getName());
        taskEntity.setData(taskBoard.getData());
        return taskEntity;
    }
    @Transactional
    public String delete(Long id){
        taskBoardRepository.deleteById((long) Math.toIntExact(id));
        return "TaskBoard Deleted";
    }
}
