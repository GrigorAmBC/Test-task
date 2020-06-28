package ru.nsu.fit.grigor.database_project.adapter.operation.utility.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class JsonHelper {
  protected final Gson gson = new GsonBuilder()
          .serializeNulls()
          .setPrettyPrinting()
          .serializeNulls()
          .excludeFieldsWithoutExposeAnnotation()
          .create();

  public String getJsonError(String errorMessage) {
    errorMessage = errorMessage.replaceAll("\"", "");
    String jsonErrorMessage = "{\"type\": \"error\"," +
            "\"message\": \"" + errorMessage + "\"}";

    JsonElement je = JsonParser.parseString(jsonErrorMessage);
    return gson.toJson(je);
  }
}






