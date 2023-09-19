package com.example.demo;

import com.example.demo.model.Dog;
import com.example.demo.service.DogService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest @DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class DogServiceTest {

  @Autowired
  private DogService dogService;

  ObjectMapper mapper = new ObjectMapper();



  // dog save
  // dog find - can not find
  // dog update
  // dog delete

  @Test
  public void entitySavedSuccessfully() {
    Dog dog = new Dog("AAA", "BBB", "CCC", true, null);
    dogService.save(dog);
    Optional<Dog> dogg = dogService.findById(1);

    Assertions.assertEquals(dogg.get().getName(), "BBB");
  }

  @Test
  public void entityCannotBeFound() {
    Dog dog = new Dog("AAA", "BBB", "CCC", true, null);
    dogService.save(dog);

    Optional<Dog> dogg = dogService.findById(2);
    Assertions.assertEquals(dogg.isPresent(), false);
  }

  @Test
  public void entityUpdatedSuccessfully() {
    Dog dog = new Dog("AAA", "BBB", "CCC", true, null);
    dogService.save(dog);

    Optional<Dog> dogg = dogService.findById(1);
    dogg.get().setCanSit(false);
    dogService.save(dogg.get());

    Assertions.assertEquals(dogService.findById(1).get().isCanSit(), false);
  }

  @Test
  public void deleteEntity() {
    Dog dog = new Dog("AAA", "BBB", "CCC", true, null);
    dogService.save(dog);
    Assertions.assertEquals(dogService.findById(1).isPresent(), true);
    dogService.deleteById(1);
    Assertions.assertEquals(dogService.findById(1).isPresent(), false);
  }

  @Test
  public void deleteAll() {
    Dog dog = new Dog("AAA", "BBB", "CCC", true, null);
    Dog dog1 = new Dog("ASD", "JKL", "FFF", true, null);
    Dog dog2 = new Dog("DDD", "VVV", "SSS", false, null);

    List<Dog> dogs = Arrays.asList(dog, dog1, dog2);
    dogService.saveAll(dogs);
    Assertions.assertEquals(dogService.findById(1).isPresent(), true);
    Assertions.assertEquals(dogService.findById(2).isPresent(), true);
    Assertions.assertEquals(dogService.findById(3).isPresent(), true);

    dogService.deleteAll();
    Assertions.assertEquals(dogService.findById(1).isPresent(), false);
    Assertions.assertEquals(dogService.findById(2).isPresent(), false);
    Assertions.assertEquals(dogService.findById(3).isPresent(), false);
  }

  @Test
  public void findByBreedSuccessful() {
    Dog dog = new Dog("AAA", "BBB", "CCC", true, null);
    Dog dogg = new Dog("AAA", "BBB", "CCC", false, null);
    dogService.save(dog);
    dogService.save(dogg);

    Dog dog1 = dogService.findByBreed("AAA").get(0);
    Dog dog2 = dogService.findByBreed("AAA").get(1);

    Assertions.assertEquals(dog1.isCanSit(), true);
    Assertions.assertEquals(dog2.isCanSit(), false);
  }

  @Test
  public void findByCanSitSuccessful() throws JsonProcessingException {
    Dog dog = new Dog("AAA", "BBB", "CCC", true, null);
    Dog dogg = new Dog("AAA", "BBB", "CCC", false, null);

    dogService.save(dog);
    dogService.save(dogg);

    List<Dog> sitDogs = dogService.findByCanSit(true);
    List<Dog> a = List.of(dog);

    Assertions.assertEquals(this.mapper.writeValueAsString(sitDogs), this.mapper.writeValueAsString(a));
  }
}
