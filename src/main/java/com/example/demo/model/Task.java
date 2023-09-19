package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Tasks")
public class Task {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column
  private String name;

  @Column
  private int period;


  @ManyToMany(mappedBy = "tasks")
  @JsonIgnore
  private List<Person> personal;


  public Task() {
  }

  public Task(String name, int period) {
    this.name = name;
    this.period = period;
  }
}
