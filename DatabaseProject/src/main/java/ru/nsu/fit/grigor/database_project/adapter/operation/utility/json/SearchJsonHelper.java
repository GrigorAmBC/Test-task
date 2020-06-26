package ru.nsu.fit.grigor.database_project.adapter.operation.utility.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ru.nsu.fit.grigor.database_project.adapter.operation.criteria.CriteriaFactory;
import ru.nsu.fit.grigor.database_project.model.port.Criteria;
import ru.nsu.fit.grigor.database_project.model.port.Operation;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class SearchJsonHelper extends JsonHelper {
  private final JsonObject resultJsonObject = new JsonObject();
  private final List<JsonElement> criteriaResults = new ArrayList<>();

  public SearchJsonHelper() {
    resultJsonObject.add("type", gson.toJsonTree(Operation.OperationType.search.toString()));
  }

  public void putCriteriaErrorMessage(String errorMessage, Criteria criteria) {
    JsonObject jo = new JsonObject();
    jo.add("criteria", gson.toJsonTree(criteria));

    String jsonErrorMessage = "[{\"type\": \"error\"," +
            "\"message\": \"" + errorMessage + "\"}]";

    JsonElement je = JsonParser.parseString(jsonErrorMessage);
    jo.add("results", je);

    criteriaResults.add(jo);
  }

  public void addCriteriaResult(String jsonArray, Criteria criteria) {
    if (jsonArray == null) {
      jsonArray = "[empty]";
    }
    JsonObject jo = new JsonObject();
    jo.add("criteria", gson.toJsonTree(criteria));

    JsonElement je = JsonParser.parseString(jsonArray);
    jo.add("results", je);

    criteriaResults.add(jo);
  }

  public String getJsonResult() {
    if (!resultJsonObject.has("results")) {
      JsonElement je = gson.toJsonTree(criteriaResults);
      resultJsonObject.add("results", je);
    }
    return gson.toJson(resultJsonObject);
  }

  public void setupCriteriaList(Reader reader, List<Criteria> criteriaList) throws IllegalArgumentException {
    JsonArray jsonCriteriaArray = getJsonArray(reader);

    for (JsonElement element : jsonCriteriaArray) {
      JsonObject jo = element.getAsJsonObject();
      if (jo.keySet().isEmpty()) {
        throw new IllegalArgumentException("missing criterias in input file");
      }
      String criteriaName = jo.keySet().iterator().next();

      Criteria criteria;
      Class<?> className;
      try {
        className = CriteriaFactory.getCriteriaClass(criteriaName);
        criteria = (Criteria) gson.fromJson(jo, className);
      } catch (IllegalArgumentException e) {
        throw new IllegalArgumentException("one of criterias is wrong");
      } /*catch (JsonSyntaxException e) {todo
        throw new IllegalArgumentException("wrong criteria json syntax");
      }*/

      criteriaList.add(criteria);
    }
  }

  private JsonArray getJsonArray(Reader reader) {
    JsonArray jsonCriteriaArray;
    try {
      jsonCriteriaArray = JsonParser.parseReader(reader)
              .getAsJsonObject()
              .getAsJsonArray("criterias");
    } catch (Exception e) {
      throw new IllegalArgumentException("wrong json format of input file");
    }

    if (jsonCriteriaArray == null || jsonCriteriaArray.size() == 0) {
      throw new IllegalArgumentException("missing criterias in input file");
    }
    return jsonCriteriaArray;
  }
}