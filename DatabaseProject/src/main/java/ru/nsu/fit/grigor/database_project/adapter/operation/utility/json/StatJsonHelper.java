package ru.nsu.fit.grigor.database_project.adapter.operation.utility.json;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.Reader;
import java.util.Map;

public class StatJsonHelper extends JsonHelper {
  public String getNiceJson(String json) {
    JsonObject jo = JsonParser.parseString(json).getAsJsonObject();
    return gson.toJson(jo);
  }

  public Map<String, String> getInputParams(Reader reader) {
    Map<String, String> params;
    try {
      params = gson.fromJson(reader, new TypeToken<Map<String, String>>() {
      }.getType());
    } catch (JsonSyntaxException e) {
      throw new IllegalArgumentException("wrong json format of input file");
    }

    return params;
  }

}
