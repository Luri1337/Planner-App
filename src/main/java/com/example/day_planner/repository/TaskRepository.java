package com.example.day_planner.repository;

import com.example.day_planner.model.Priority;
import com.example.day_planner.model.Task;
import com.example.day_planner.model.TaskStatus;
import com.example.day_planner.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByNameContainingIgnoreCase(String name);

    // Фильтрация задач по дате выполнения
    List<Task> findByDueDate(LocalDate dueDate);

    // Фильтрация задач по статусу
    List<Task> findByStatus(TaskStatus status);

    List<Task> findByPriority(Priority priority);
    List<Task> findByUser(User user);
}
