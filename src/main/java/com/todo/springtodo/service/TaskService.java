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
import java.util.UUID;


@RequiredArgsConstructor
@Service
public class TaskService {

    @Autowired
    private final TaskBoardRepository taskBoardRepository;
    @Autowired
    private final TaskRepository taskRepository;
    @Transactional
    public TaskBoard create(TaskBoard taskBoard){
        return taskBoardRepository.save(taskBoard);
    }

    @Transactional
    public TaskBoard createAll(Long boardId, List<Task> tasks){
        Task taskEntity = new Task();
        TaskBoard taskBoardEntity = taskBoardRepository.findById( Math.toIntExact(boardId))
                .orElseThrow(()->new IllegalArgumentException("check Id"));
        List<Task> taskList = taskBoardEntity.getTaskList();
        System.out.println("The taskList is: "+ taskList);
        if(taskList.size() >0) {
            System.out.println("entered into the loop");
            for (Task task : taskList) {
                System.out.println(task.getTaskId());
                Long taskId = Long.valueOf(task.getTaskId());
                removeTask(taskId);
            }
        }
        for(Task task: tasks) {
            taskEntity.setTaskName(task.getTaskName());
            taskEntity.setTaskBoard(taskBoardEntity);
            List<Task> taskList2 = taskBoardEntity.getTaskList();
            taskList2.add(taskEntity);
            taskBoardEntity.setTaskList(taskList2);
            taskBoardRepository.save(taskBoardEntity);
        }
        return taskBoardEntity;
    }

    @Transactional
    public void deleteAllTasks() {
        taskRepository.deleteAll();
    }

    @Transactional(readOnly = true)
    public List<TaskBoard> findAll(){
        return taskBoardRepository.findAll();
    }

    @Transactional
    public TaskBoard update(Long id, Task task){
        TaskBoard taskBoardEntity = taskBoardRepository.findById( Math.toIntExact(id))
                .orElseThrow(()->new IllegalArgumentException("check Id"));  //Persistence Context
        Task taskEntity = new Task();
        taskEntity.setTaskName(task.getTaskName());
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

    @Transactional
    public String deleteTask(Long id){
        taskRepository.deleteById(Math.toIntExact(id));
        return "TaskBoard Deleted";
    }
    @Transactional
    public void removeTask(Long id){
        taskRepository.deleteById(Math.toIntExact(id));
    }

    public String updateTask(Long taskId, Task task) {
        System.out.println("entered in to the updateTask function");
        Task taskEntity = taskRepository.findById(Math.toIntExact(taskId)).
                orElseThrow(()->new IllegalArgumentException("check Id"));
        System.out.println("The task entity is: "+ taskEntity.getTaskId());
        System.out.println("The task name is: "+ task.getTaskName());
        taskEntity.setTaskName(task.getTaskName());
        taskRepository.save(taskEntity);
        return "Task Updated";
    }
}
