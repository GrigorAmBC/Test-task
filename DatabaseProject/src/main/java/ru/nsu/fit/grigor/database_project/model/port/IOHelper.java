package ru.nsu.fit.grigor.database_project.model.port;

import com.google.gson.JsonObject;

public interface IOHelper {
  void writeResultToOut();//todo:change params
  void readFromIn();
  void writeErrorToOut(String message);
}
