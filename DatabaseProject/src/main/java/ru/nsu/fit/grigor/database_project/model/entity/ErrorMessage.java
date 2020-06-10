package ru.nsu.fit.grigor.database_project.model.entity;

public class ErrorMessage {
  String type = "error";
  String message;

  public ErrorMessage(String message) {
    this.message = message;
  }
}
