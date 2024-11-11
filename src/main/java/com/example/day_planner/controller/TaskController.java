package com.example.day_planner.controller;

import com.example.day_planner.exception.TaskNotFoundException;
import com.example.day_planner.model.Priority;
import com.example.day_planner.model.Task;
import com.example.day_planner.model.TaskStatus;
import com.example.day_planner.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@Validated
public class TaskController {
    @Autowired
    private TaskService taskService;
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @Valid @RequestBody Task task) {
        return taskService.updateTask(id, task);
    }

    @GetMapping("/search")
    public List<Task> searchTasksByName(@RequestParam String name) {
        return taskService.findTasksByName(name);
    }

    // Фильтрация задач по дате
    @GetMapping("/filter-by-date")
    public List<Task> filterTasksByDueDate(@RequestParam LocalDate dueDate) {
        return taskService.findTasksByDueDate(dueDate);
    }

    @GetMapping("/filter-by-status")
    public List<Task> filterTasksByStatus(@RequestParam TaskStatus status) {
        return taskService.findTasksByStatus(status);
    }

    @GetMapping("/filter-by-priority")
    public List<Task> filterTasksByPriority(@RequestParam Priority priority) {
        return taskService.findTasksByPriority(priority);
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @PostMapping
    public Task createTask(@Valid @RequestBody Task task) {
        return taskService.createTask(task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));
    }
    @GetMapping("/tasks")
    public List<Task> getUserTasks() {
        return taskService.getUserTasks();
    }

}
