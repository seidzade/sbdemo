package com.java2016.sbdemo.domain;

import lombok.Data;

@Data
public class Artist {
  private long id;
  private String name;
  private int yearOfBirth;
  private int yearOfDeath;
}
