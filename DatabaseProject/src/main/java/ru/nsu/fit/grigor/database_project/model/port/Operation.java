package ru.nsu.fit.grigor.database_project.model.port;

import org.jetbrains.annotations.NotNull;

import java.io.*;

public interface Operation {
  enum OperationType {
    search, stat
  }
  

  void makeOperation(Writer writer);
  void setParameters(Reader reader);
}
