package com.example.demo.controller;

import com.example.demo.model.Dog;
import com.example.demo.repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8081")
public class DogController {

  @Autowired
  DogRepository dogRepository;

  @GetMapping("/dogs")
  public ResponseEntity<List<Dog>> getAllDogs(@RequestParam(required = false) String breed) {
    try {
      List<Dog> dogs = new ArrayList<>();

      if (breed == null) {
        dogRepository.findAll().forEach(dogs::add);
      } else {
        dogRepository.findByBreed(breed).forEach(dogs::add);
      }

      if (dogs.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
        return new ResponseEntity<>(dogs, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/dogs/{id}")
  public ResponseEntity<Dog> getDogById(@PathVariable("id") long id) {
    Optional<Dog> dogData = dogRepository.findById(id);

    if (dogData.isPresent()) {
      return new ResponseEntity<>(dogData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/dogs")
  public ResponseEntity<Dog> createDog(@RequestBody Dog dog) {
    try {
      Dog dogg = dogRepository
              .save(new Dog(dog.getBreed(), dog.getName(), dog.getColor(), false));
      return new ResponseEntity<>(dogg, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/dogs/{id}")
  public ResponseEntity<Dog> updateDog(@PathVariable("id") long id, @RequestBody Dog dog) {
    Optional<Dog> dogData = dogRepository.findById(id);

    if (dogData.isPresent()) {
      Dog dogg = dogData.get();
      dogg.setBreed(dog.getBreed());
      dogg.setColor(dog.getColor());
      dogg.setCanSit(dog.isCanSit());
      return new ResponseEntity<>(dogRepository.save(dogg), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/dogs/{id}")
  public ResponseEntity<Dog> deleteDog(@PathVariable("id") long id) {
    try {
      dogRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/dogs")
  public ResponseEntity<HttpStatus> deleteAllDogs() {
    try {
      dogRepository.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/dogs/canSit")
  public ResponseEntity<List<Dog>> findByCanSit() {
    try {
      List<Dog> dogs = dogRepository.findByCanSit(true);

      if (dogs.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } else {
        return new ResponseEntity<>(dogs, HttpStatus.OK);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/dogs/breed/{breed}")
  public ResponseEntity<List<Dog>> findByBreed(@PathVariable String breed) {
    try {
      List<Dog> dogs = dogRepository.findByBreed(breed);

      if (dogs.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } else {
        return new ResponseEntity<>(dogs, HttpStatus.OK);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
