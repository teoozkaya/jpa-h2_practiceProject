package com.example.demo.service.impl;

import com.example.demo.model.Dog;
import com.example.demo.repository.DogRepository;
import com.example.demo.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DogServiceImpl implements DogService {

  @Autowired
  DogRepository dogRepository;

  @Override
  public List<Dog> findAll() {
    return dogRepository.findAll();
  }

  @Override
  public Optional<Dog> findById(long id) {
    return dogRepository.findById(id);
  }

  @Override
  public Dog save(Dog dog) {
    return dogRepository.save(dog);
  }

  @Override
  public List<Dog> saveAll(List<Dog> dogs) {
    return dogRepository.saveAll(dogs);
  }

  @Override
  public void deleteById(long id) {
    dogRepository.deleteById(id);
  }

  @Override
  public void deleteAll() {
    dogRepository.deleteAll();
  }

  @Override
  public List<Dog> findByCanSit(boolean canSit) {
    return dogRepository.findByCanSit(canSit);
  }

  @Override
  public List<Dog> findByBreed(String breed) {
    return dogRepository.findByBreed(breed);
  }
}
