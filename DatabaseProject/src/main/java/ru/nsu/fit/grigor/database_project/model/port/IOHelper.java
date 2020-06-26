package ru.nsu.fit.grigor.database_project.model.port;

import java.io.FileNotFoundException;
import java.io.Reader;

public interface IOHelper {
  Reader getInputReader() throws FileNotFoundException;
  void writeToOut(String content);
}
