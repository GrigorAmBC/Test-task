package ru.nsu.fit.grigor.database_project;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ru.nsu.fit.grigor.database_project.adapter.JsonIOHelper;
import ru.nsu.fit.grigor.database_project.model.entity.ErrorMessage;
import ru.nsu.fit.grigor.database_project.model.port.IOHelper;

public class Main {
  //todo: change configuration to Maven
  //java -jar program.jar search input.json output.json
  private static ResultSeeker resultSeeker;
  private static IOHelper ioHelper;
  public static void main(String[] args) {
    if (args.length != 3) {
      String error = new Gson().toJson(new ErrorMessage("Too few command line arguments."));
      ioHelper = new JsonIOHelper();
      ioHelper.writeErrorToOut(error);
      return;
    }
//todo: delete 1st 2 args
    resultSeeker = new ResultSeeker(args[1], args[2], args[0], new JsonIOHelper(args[0], args[1]));
    resultSeeker.executeOperation();
  }


}
