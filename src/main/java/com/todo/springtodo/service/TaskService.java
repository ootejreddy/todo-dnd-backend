package com.todo.springtodo.service;

import com.todo.springtodo.domain.Task;
import com.todo.springtodo.domain.TaskBoard;
import com.todo.springtodo.repository.TaskBoardRepository;
import com.todo.springtodo.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
public class TaskService {

    private final TaskBoardRepository taskBoardRepository;
    private final TaskRepository taskRepository;
    @Transactional
    public TaskBoard create(TaskBoard taskBoard){
        return taskBoardRepository.save(taskBoard);
    }

    @Transactional(readOnly = true)
    public List<TaskBoard> findAll(){
        return taskBoardRepository.findAll();
    }

    @Transactional
    public TaskBoard update(Long id, String task){
        TaskBoard taskBoardEntity = taskBoardRepository.findById( Math.toIntExact(id))
                .orElseThrow(()->new IllegalArgumentException("check Id"));  //Persistence Context
        Task taskEntity = new Task();
        taskEntity.setTaskName(task);
        taskEntity.setTaskBoard(taskBoardEntity);
        List<Task> taskList = taskBoardEntity.getTaskList();
        taskList.add(taskEntity);
        taskBoardEntity.setTaskList(taskList);
        taskBoardRepository.save(taskBoardEntity);
        return taskBoardEntity;
    }
    @Transactional
    public String delete(Long id){
        taskBoardRepository.deleteById(Math.toIntExact(id));
        return "TaskBoard Deleted";
    }
}
