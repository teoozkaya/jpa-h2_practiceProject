package com.example.demo.service;

import com.example.demo.model.Person;
import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface TaskService {

  List<Task> findAll();

  Optional<Task> findById(long id);

  Task save(Task task);

  void deleteById(long id);

  void deleteAll();

}
