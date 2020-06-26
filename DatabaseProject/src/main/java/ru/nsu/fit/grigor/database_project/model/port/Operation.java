package ru.nsu.fit.grigor.database_project.model.port;

import java.io.Reader;

public interface Operation {
  enum OperationType {
    search, stat
  }
  
  void makeOperation(IOHelper ioHelper);
  void setParameters(Reader reader) throws IllegalArgumentException;
}
