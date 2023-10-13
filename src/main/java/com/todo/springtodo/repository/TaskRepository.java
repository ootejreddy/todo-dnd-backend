package com.todo.springtodo.repository;

import com.todo.springtodo.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, Integer> {
}
