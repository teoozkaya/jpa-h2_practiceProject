package com.example.demo.controller;

import com.example.demo.model.Person;
import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;
import com.example.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TaskController {

  @Autowired
  TaskService taskService;


  @GetMapping("/tasks")
  public ResponseEntity<List<Task>> getAllTasks() {
    try {
      List<Task> tasks = taskService.findAll();
      if (tasks.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
      } else {
        return ResponseEntity.ok(tasks);
      }
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @GetMapping("/tasks/{id}")
  public ResponseEntity<Task> getTaskByID(@PathVariable("id") long id) {
    Optional<Task> task = taskService.findById(id);

    if (task.isPresent()) {
      return ResponseEntity.ok(task.get());
    } else {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
  }

  @PostMapping("/tasks")
  public ResponseEntity<Task> createTask(@RequestBody Task task){
    try {
      Task returnedTask = taskService.save(task);
      return ResponseEntity.ok(returnedTask);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @PutMapping("/tasks/{id}")
  public ResponseEntity<Task> updateTask(@PathVariable("id") long id, @RequestBody Task task) {
    Optional<Task> taskOptional= taskService.findById(id);

    if (taskOptional.isPresent()) {
      Task task1 = taskOptional.get();
      if (task.getName() != null) task1.setName(task.getName());
      if (task.getPeriod() != 0) task1.setPeriod(task.getPeriod());
      return ResponseEntity.ok(task1);
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

  @DeleteMapping("/tasks/{id}")
  public ResponseEntity<Task> deleteTask(@PathVariable("id") long id) {
    try {
      taskService.deleteById(id);
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

  @DeleteMapping("/tasks")
  public ResponseEntity<Person> deleteAllTasks() {
    try {
      taskService.deleteAll();
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
}
