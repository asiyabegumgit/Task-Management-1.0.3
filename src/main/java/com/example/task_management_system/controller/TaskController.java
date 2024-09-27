package com.example.task_management_system.controller;

import com.example.task_management_system.model.Task;
import com.example.task_management_system.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@Slf4j
@Controller
@RequestMapping("/tasks")
public class TaskController {
  @Autowired
  private TaskService taskService;
  @GetMapping
    public String getAllTask(Model model) {
    log.info("Fetching all Tasks");
    List<Task> tasks=taskService.getAllTasks();
    model.addAttribute("tasks", tasks);
    return "task-list";
  }

  @GetMapping("/new")
  public String showCreateTaskForm(Model model) {
    log.info("Displaying task creation form");
    Task task = new Task();
    model.addAttribute("task", task);
    return "task-form";
  }


  @GetMapping("/edit/{id}")
  public String showEditTaskForm(@PathVariable Long id, Model model) {
     log.info("Fetching product with ID: {}", id);
    Optional<Task> optionalTask = taskService.getTaskById(id);
    if (optionalTask.isPresent()) {
      log.info("Task found: {}", optionalTask.get());
      model.addAttribute("task", optionalTask.get());
    } else {
      log.warn("Task with ID: {} not found", id);
      return "redirect:/tasks";
    }
    return "task-form";
  }

  @PostMapping("/save")
  public String saveTask(@ModelAttribute("task") Task task) {
    
    if (task.getId() != null) {
       log.info("Updating product: {}", task);
      taskService.updateTask(task.getId(), task);
    } else {
      log.info("Creating new task: {}", task);
      taskService.createTask(task);
    }
    return "redirect:/tasks";
  }

  @GetMapping("/delete/{id}")
  public String deleteTask(@PathVariable Long id) {
     log.info("Deleting task with ID: {}", id);
    taskService.deleteTask(id);
    return "redirect:/tasks";
  }

}

