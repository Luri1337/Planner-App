package com.example.day_planner.service;

import com.example.day_planner.model.Priority;
import com.example.day_planner.model.Task;
import com.example.day_planner.model.TaskStatus;
import com.example.day_planner.model.User;
import com.example.day_planner.repository.TaskRepository;
import com.example.day_planner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    public Task updateTask(Long id, Task updatedTask) {
        Optional<Task> existingTaskOpt = taskRepository.findById(id);
        if (existingTaskOpt.isPresent()) {
            Task existingTask = existingTaskOpt.get();
            existingTask.setName(updatedTask.getName());
            existingTask.setDescription(updatedTask.getDescription());
            existingTask.setDueDate(updatedTask.getDueDate());
            return taskRepository.save(existingTask);
        } else {
            throw new RuntimeException("Task with id " + id + " not found");
        }
    }

    public List<Task> findTasksByName(String name) {
        return taskRepository.findByNameContainingIgnoreCase(name);
    }

    // Фильтрация задач по дате выполнения
    public List<Task> findTasksByDueDate(LocalDate dueDate) {
        return taskRepository.findByDueDate(dueDate);
    }

    // Фильтрация задач по статусу
    public List<Task> findTasksByStatus(TaskStatus status) {
        return taskRepository.findByStatus(status);
    }

    public List<Task> findTasksByPriority(Priority priority) {
        return taskRepository.findByPriority(priority);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task createTask(Task task) {return taskRepository.save(task);}

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }
    public List<Task> getUserTasks() {
        // Получаем текущий email пользователя
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        // Находим пользователя по email
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Возвращаем задачи пользователя
        return taskRepository.findByUser(user);
    }
}
