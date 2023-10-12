package com.todo.springtodo.repository;

import com.todo.springtodo.domain.TaskBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskBoardRepository extends JpaRepository<TaskBoard, Integer> {
}