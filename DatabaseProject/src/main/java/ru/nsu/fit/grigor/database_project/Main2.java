package ru.nsu.fit.grigor.database_project;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import ru.nsu.fit.grigor.database_project.adapter.operation.criteria.BadCustomersCriteria;
import ru.nsu.fit.grigor.database_project.adapter.operation.criteria.LastNameCriteria;
import ru.nsu.fit.grigor.database_project.adapter.operation.criteria.MinExpensesCriteria;
import ru.nsu.fit.grigor.database_project.adapter.operation.criteria.ProductNameCriteria;
import ru.nsu.fit.grigor.database_project.model.port.Criteria;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main2 {
  public static void main(String[] args) {
    String json = "{\n" +
            "\"criterias\": [ \n" +
            "{\"lastName\": \"Иванов\"}, //Фамилия\n" +
            "{\"productName\": \"Минеральная вода\", \"minTimes\": 5}, // Название товара и число раз\n" +
            "{\"minExpenses\": 112, \"maxExpenses\": 4000}, //Минимальная и максимальная стоимость всех покупок\n" +
            "{\"badCustomers\": 3} //Число пассивных покупателей\n" +
            "]\n" +
            "}";
    Gson gson = new GsonBuilder().create();//todo
    List<Criteria> criteriaList = new ArrayList<>();
    JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();

    JsonArray jsonArray = jsonObject.getAsJsonArray("criterias");

    for (JsonElement element : jsonArray) {
      criteriaList.add(getCriteria(element.getAsJsonObject(), gson));
    }

  }

  private static Criteria getCriteria(JsonObject obj, Gson gson) {
    Criteria criteria = null;
    for (String str : obj.keySet()) {
      switch (str) {
        case "lastName":
          criteria = gson.fromJson(obj, new TypeToken<LastNameCriteria>() {
          }.getType());
          break;
        case "productName":
          criteria = gson.fromJson(obj, new TypeToken<ProductNameCriteria>() {
          }.getType());
          break;
        case "minExpenses":
          criteria = gson.fromJson(obj, new TypeToken<MinExpensesCriteria>() {
          }.getType());
          break;
        case "badCustomers":
          criteria = gson.fromJson(obj, new TypeToken<BadCustomersCriteria>() {
          }.getType());
          break;
        default:
          //todo: throw new IllegalArgumentException
          break;
      }
    }
    return criteria;
  }
}
