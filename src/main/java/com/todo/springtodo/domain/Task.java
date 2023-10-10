package com.todo.springtodo.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    private long taskId;
    private String name;
    @ManyToOne
    private TaskBoard board;
}
