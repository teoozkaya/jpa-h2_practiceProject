package com.example.demo.controller;

import com.example.demo.model.Dog;
import com.example.demo.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DogController {

  @Autowired
  DogService dogService;

  @GetMapping("/dogs")
  public ResponseEntity<List<Dog>> getAllDogs() {
    try {
      List<Dog> dogs = dogService.findAll();

      if (dogs.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
      }
      return ResponseEntity.ok(dogs);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @GetMapping("/dogs/{id}")
  public ResponseEntity<Dog> getDogById(@PathVariable("id") long id) {
    Optional<Dog> dogData = dogService.findById(id);

    if (dogData.isPresent()) {
      return ResponseEntity.ok(dogData.get());
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

  @PostMapping("/dogs")
  public ResponseEntity<Dog> createDog(@RequestBody Dog dog) {
    try {
      Dog dogg = new Dog(dog.getBreed(), dog.getName(), dog.getColor(), false, dog.getOwner());
      Dog savedDog = dogService.save(dogg);
      return ResponseEntity.status(HttpStatus.CREATED).body(savedDog);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }


  @PutMapping("/dogs/{id}")
  public ResponseEntity<Dog> updateDog(@PathVariable("id") long id, @RequestBody Dog dog) {
    Optional<Dog> dogData = dogService.findById(id);

    if (dogData.isPresent()) {
      Dog dogg = dogData.get();
      if (dog.getName() != null) {dogg.setName(dog.getName()); }
      if (dog.getBreed() != null) {dogg.setBreed(dog.getBreed()); }
      if (dog.getColor() != null) {dogg.setColor(dog.getColor()); }
      if (!dog.isCanSit()) {dogg.setCanSit(dog.isCanSit()); }
      if (dog.getOwner() != null) { dogg.setOwner(dog.getOwner()); }
      return ResponseEntity.ok(dogService.save(dogg));
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

  @DeleteMapping("/dogs/{id}")
  public ResponseEntity<Dog> deleteDog(@PathVariable("id") long id) {
    try {
      dogService.deleteById(id);
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @DeleteMapping("/dogs")
  public ResponseEntity<HttpStatus> deleteAllDogs() {
    try {
      dogService.deleteAll();
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @GetMapping("/dogs/canSit")
  public ResponseEntity<List<Dog>> findByCanSit() {
    try {
      List<Dog> dogs = dogService.findByCanSit(true);

      if (dogs.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
      } else {
        return ResponseEntity.ok(dogs);
      }
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @GetMapping("/dogs/breed/{breed}")
  public ResponseEntity<List<Dog>> findByBreed(@PathVariable String breed) {
    try {
      List<Dog> dogs = dogService.findByBreed(breed);

      if (dogs.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
      } else {
        return ResponseEntity.ok(dogs);
      }
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
}
