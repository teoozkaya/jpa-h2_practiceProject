package com.example.demo.service.impl;

import com.example.demo.model.Person;
import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;
import com.example.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

  @Autowired
  TaskRepository taskRepository;

  @Override
  public List<Task> findAll() {
    return taskRepository.findAll();
  }

  @Override
  public Optional<Task> findById(long id) {
    return taskRepository.findById(id);
  }

  @Override
  public Task save(Task task) {
    return taskRepository.save(task);
  }

  @Override
  public void deleteById(long id) {
    taskRepository.deleteById(id);
  }

  @Override
  public void deleteAll() {
    taskRepository.deleteAll();
  }
}
