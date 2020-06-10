package ru.nsu.fit.grigor.database_project.adapter.operation;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import ru.nsu.fit.grigor.database_project.adapter.DAOHelper;
import ru.nsu.fit.grigor.database_project.adapter.JsonHelper;
import ru.nsu.fit.grigor.database_project.adapter.JsonResult;
import ru.nsu.fit.grigor.database_project.adapter.operation.criteria.BadCustomersCriteria;
import ru.nsu.fit.grigor.database_project.adapter.operation.criteria.LastNameCriteria;
import ru.nsu.fit.grigor.database_project.adapter.operation.criteria.MinExpensesCriteria;
import ru.nsu.fit.grigor.database_project.adapter.operation.criteria.ProductNameCriteria;
import ru.nsu.fit.grigor.database_project.model.port.Criteria;
import ru.nsu.fit.grigor.database_project.model.port.Operation;

import java.io.Reader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchOperation implements Operation {
  JsonHelper jsonHelper = new JsonHelper();
  DAOHelper daoHelper;
  List<Criteria> criteriaList = new ArrayList<>();

  public SearchOperation() {
    String url = "jdbc:postgresql://localhost:5432/contactdb";
    String login = "postgres";
    String password = "postgres";
    daoHelper = new DAOHelper(url, login, password);
  }

  @Override
  public void makeOperation(Writer writer) {


    for (Criteria criteria : criteriaList) {
      JsonResult result = criteria.getCriteriaResult(daoHelper, jsonHelper);
      // todo: write result to file
    }
  }

  @Override
  public void setParameters(Reader reader) {
    Gson gson = new Gson();//todo

    JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
    JsonArray jsonArray = jsonObject.getAsJsonArray("criterias");

    for (JsonElement element : jsonArray) {
      criteriaList.add(getCriteria(element.getAsJsonObject(), gson));
    }
  }

  private JsonObject executeCriteria(Criteria criteria, Connection con) {
    //todo: change it, delegate to
    //todo: another class working with json (putting)



    try {
      Statement stmt = con.createStatement();
      criteria.getCriteriaResult();
      ResultSet rs = stmt.executeQuery("SELECT * FROM JC_CONTACT");
      while (rs.next()) {
        String str = rs.getString("contact_id")+":" +rs.getString(2);
        System.out.println("Contact:" + str);
      }
      rs.close();
      stmt.close();
    } catch (Exception e) {
      e.printStackTrace();//todo:
    } finally {
      con.close();
    }




    return null;
  }

  private Criteria getCriteria(JsonObject obj, Gson gson) {
    Criteria criteria = null;
    String str = obj.keySet().iterator().next();
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

    return criteria;
  }


  private Criteria getCriteria(Map<String, String> criteriaMap) {
    if (criteriaMap.containsKey("lastName")) {
      return new LastNameCriteria(criteriaMap);
    } else if (criteriaMap.containsKey("productName")) {
      return new ProductNameCriteria(criteriaMap);
    } else if (criteriaMap.containsKey("minExpenses")) {
      return new MinExpensesCriteria(criteriaMap);
    } else if (criteriaMap.containsKey("badCustomers")) {
      return new BadCustomersCriteria(criteriaMap);
    } else {
      //todo:throw new IllegalArgumentException("No criteria");
    }

    return null;
  }
}
