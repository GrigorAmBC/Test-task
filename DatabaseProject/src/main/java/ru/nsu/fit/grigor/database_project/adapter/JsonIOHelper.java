package ru.nsu.fit.grigor.database_project.adapter;

import com.google.gson.Gson;
import ru.nsu.fit.grigor.database_project.model.entity.ErrorMessage;
import ru.nsu.fit.grigor.database_project.model.port.IOHelper;

public class JsonIOHelper implements IOHelper { //todo: rename
  private String inFileName = "input.json";//defaults
  private String outFileName = "output.json";

  public JsonIOHelper() {

  }

  public JsonIOHelper(String inFileName, String outFileName) {
    this.inFileName = inFileName;
    this.outFileName = outFileName;
  }

  @Override
  public void writeResultToOut() {

  }

  @Override
  public void readFromIn() {

  }

  @Override
  public void writeErrorToOut(String message) {
    String error = new Gson().toJson(new ErrorMessage("Too few command line arguments."));
  }
}
