package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "Dogs")
public class Dog {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(name = "breed")
  private String breed;

  @Column(name = "name")
  private String name;

  @Column(name = "color")
  private String color;

  @Column(name = "canSit")
  private boolean canSit;

  @ManyToOne
  @JoinColumn(name = "owner")
  private Person owner;

  public Dog(String breed, String name, String color, boolean canSit, Person owner) {
    this.breed = breed;
    this.name = name;
    this.color = color;
    this.canSit = canSit;
    this.owner = owner;
  }


}
