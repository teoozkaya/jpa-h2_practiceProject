package com.example.demo.controller;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8081")
public class PersonController {


  @Autowired
  PersonService personService;

  // TODO:
  //  -delete,
  //  -deleteAllPeople

  @GetMapping("/people")
  public ResponseEntity<java.util.List<Person>> getAllPeople() {
    try {
      List<Person> people = personService.findAll();
      if (people.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
      } else {
        return ResponseEntity.ok(people);
      }
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @GetMapping("/people/{id}")
  public ResponseEntity<Person> getPersonByID(@PathVariable("id") long id) {
    Optional<Person> person = personService.findById(id);

    if (person.isPresent()) {
      return ResponseEntity.ok(person.get());
    } else {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
  }

  @PostMapping("/people")
  public ResponseEntity<Person> createPerson(@RequestBody Person person){
    try {
    Person returnPerson = personService.save(person);
    return ResponseEntity.ok(returnPerson);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @PutMapping("/people/{id}")
  public ResponseEntity<Person> updatePerson(@PathVariable("id") long id, @RequestBody Person person) {
    Optional<Person> personOptional = personService.findById(id);

    if (personOptional.isPresent()) {
      Person person1 = personOptional.get();
      if (person.getName() != null) person1.setName(person.getName());
      if (person.getDogs() != null) person1.setDogs(person.getDogs());
      if (person.getPartner() != null) person1.setPartner(person.getPartner());
      if (person.getTasks() != null) person1.setTasks(person.getTasks());
      return ResponseEntity.ok(person1);
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

  @DeleteMapping("/people/{id}")
  public ResponseEntity<Person> deletePerson(@PathVariable("id") long id) {
    try {
      personService.deleteById(id);
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

  @DeleteMapping("/people")
  public ResponseEntity<Person> deleteAllPeople() {
    try {
    personService.deleteAll();
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

}
