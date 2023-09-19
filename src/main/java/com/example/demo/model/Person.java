package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "People")
public class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column
  private String name;

  @OneToOne
  @JoinColumn(name = "partner-id")
  @JsonIgnore
  private Person partner;


  @ManyToMany
  @JoinTable(
          name = "personal_task",
          joinColumns = @JoinColumn(name = "person-id"),
          inverseJoinColumns = @JoinColumn(name = "task-id")
  )
  private List<Task> tasks;


  @OneToMany(
          mappedBy = "owner",
          cascade = CascadeType.ALL,
          orphanRemoval = true
  )
  @JsonIgnore
  private List<Dog> dogs;

  public Person(String name) {
    this.name = name;
  }

}
