package com.java2016.sbdemo.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity(name="artists")
public class Artist {
  @Id
  private long id;
  private String name;
  private int yearOfBirth;
  private int yearOfDeath;
}
