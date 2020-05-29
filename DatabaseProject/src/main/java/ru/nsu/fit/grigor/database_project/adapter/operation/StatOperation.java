package ru.nsu.fit.grigor.database_project.adapter.operation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ru.nsu.fit.grigor.database_project.model.port.Operation;

import java.io.Reader;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class StatOperation implements Operation {

  private Date startDate;
  private Date endDate;

  @Override
  public void makeOperation(Writer writer) {
    //todo
  }

  @Override
  public void setParameters(Reader reader) {
    Gson gson = new Gson();//todo
    Map<String, String> criteriaMap = gson.fromJson(reader, new TypeToken<Map<String, String>>(){}.getType());
    String err;
    if (criteriaMap.containsKey("startDate")) {
      String pattern = "yyyy-MM-dd";
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

      try {
        startDate = simpleDateFormat.parse(criteriaMap.get("startDate"));
      } catch (ParseException e) {
        // todo: write error to file
        //err += "No startDate";
      }
    } else {
      //todo: write err to file
    }

    if (criteriaMap.containsKey("endDate")) {
      String pattern = "yyyy-MM-dd";
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

      try {
        endDate = simpleDateFormat.parse(criteriaMap.get("startDate"));
      } catch (ParseException e) {
        // todo: write error to file
        //err += "No endDate";
      }
    } else {
      //todo: write err to file
    }
  }

}
