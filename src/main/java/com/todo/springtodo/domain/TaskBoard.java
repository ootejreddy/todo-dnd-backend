package com.todo.springtodo.domain;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Data
@Table(name="taskBoard")
@AllArgsConstructor
@NoArgsConstructor
public class TaskBoard {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="taskBoard-uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name="taskBoard_id")
    private UUID taskBoardId;
    private String name;
    @ManyToOne
    @JoinColumn(name="task_id", nullable = false)
    private Task task;
}
