package com.example.demo.service;

import com.example.demo.model.Dog;
import com.example.demo.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {

  List<Person> findAll();

  Optional<Person> findById(long id);

  Person save(Person person);

  void deleteById(long id);

  void deleteAll();

  List<Person> findByName(String name);

}
