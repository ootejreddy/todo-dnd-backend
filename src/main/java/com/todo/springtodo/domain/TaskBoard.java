package com.todo.springtodo.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "task_board")
public class TaskBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long boardId;
    private String boardName;
    @JsonManagedReference
    @OneToMany(mappedBy = "taskBoard", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    private List<Task> taskList = new ArrayList<>();
}
