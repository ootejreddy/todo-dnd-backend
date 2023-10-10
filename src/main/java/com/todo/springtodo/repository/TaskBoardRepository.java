package com.todo.springtodo.repository;

import com.todo.springtodo.domain.TaskBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskBoardRepository extends JpaRepository<TaskBoard, Long> {
}