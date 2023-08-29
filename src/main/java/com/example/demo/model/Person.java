package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
@Table(name = "Owner")
public class Person {

  @Id
  @GeneratedValue(strategy =  GenerationType.AUTO)
  private long id;

  @Column
  String name;

}
