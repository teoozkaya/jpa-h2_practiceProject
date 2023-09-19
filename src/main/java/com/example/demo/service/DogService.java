package com.example.demo.service;

import com.example.demo.model.Dog;

import java.util.List;
import java.util.Optional;

public interface DogService {
  List<Dog> findAll();

  Optional<Dog> findById(long id);

  Dog save(Dog dog);

  List<Dog> saveAll(List<Dog> dogs);

  void deleteById(long id);

  void deleteAll();

  List<Dog> findByCanSit(boolean canSit);

  List<Dog> findByBreed(String breed);
}
