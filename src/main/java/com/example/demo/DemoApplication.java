package com.example.demo;

import com.example.demo.model.Dog;
import com.example.demo.model.Person;
import com.example.demo.model.Task;
import com.example.demo.repository.PersonRepository;
import com.example.demo.repository.TaskRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);
    PersonRepository personRepository = context.getBean(PersonRepository.class);
    TaskRepository taskRepository = context.getBean(TaskRepository.class);

    Person teoman = new Person("Teoman");
    Person duru = new Person("Duru");
    Person arda = new Person("Arda");
    Dog kobe = new Dog("Cavalier", "Kobe", "Brown", true, teoman);
    Dog leydi = new Dog("Cavalier", "Leydi", "Brown", true, arda);

    Task cleanUp = new Task("cleanUp", 30);
    Task work = new Task("work", 60);

    // Set Dogs
    List<Dog> teodogs = Arrays.asList(kobe);
    List<Dog> ardasDogs = Arrays.asList(leydi);
    teoman.setDogs(teodogs);
    arda.setDogs(ardasDogs);

    // Set Partners
    personRepository.save(teoman);
    duru.setPartner(teoman);
    personRepository.save(duru);
    teoman.setPartner(duru);
    personRepository.save(arda);

    // Set Tasks
    List<Task> tasks = Arrays.asList(cleanUp, work);
    taskRepository.saveAll(tasks);
    teoman.setTasks(tasks);
    personRepository.save(teoman);
  }

}
