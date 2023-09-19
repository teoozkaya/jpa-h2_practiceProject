package com.example.demo.service.impl;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonImpl implements PersonService {

  @Autowired
  PersonRepository personRepository;

  @Override
  public List<Person> findAll() {
    return personRepository.findAll();
  }

  @Override
  public Optional<Person> findById(long id) {
    return personRepository.findById(id);
  }

  @Override
  public Person save(Person person) {
    return personRepository.save(person);
  }

  @Override
  public void deleteById(long id) {
    personRepository.deleteById(id);
  }

  @Override
  public void deleteAll() {
    personRepository.deleteAll();
  }

  @Override
  public List<Person> findByName(String name) {
    return personRepository.findByName(name);
  }
}
