package ru.nsu.fit.grigor.database_project.model.entity;

import org.jetbrains.annotations.NotNull;

public class Customer {//todo: maybe add customer ID?
  private String name;
  private String lastName;

  public Customer(@NotNull String name, @NotNull String lastName) {
    this.name = name;
    this.lastName = lastName;
  }

  public String getName() {
    return name;
  }

  public String getLastName() {
    return lastName;
  }
}
