package com.todo.springtodo.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name="task")
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "task-uuid", strategy = "org.hibernate.id.UUIDGenerator")
//    @Type(type="org.hibernate.type.UUIDCharType")
    @Column(name="id")
    private UUID taskId;
    private String taskName;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "task")
    private List<TaskBoard> taskBoardList;
//    @ManyToOne
//    private TaskBoard board;
}
