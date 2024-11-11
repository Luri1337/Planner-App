package com.example.day_planner.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Future;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name cannot be null")
    @Size(min = 1, message = "Name must not be empty")
    private String name;

    @Size(max = 200, message = "Description must not exceed 200 characters")
    private String description;

    @NotNull(message = "Due date cannot be null")
    @Future(message = "Due date must be today or in the past")
    private LocalDateTime dueDate;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Связь с пользователем

}
