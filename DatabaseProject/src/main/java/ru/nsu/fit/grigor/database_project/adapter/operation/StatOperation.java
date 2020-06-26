package ru.nsu.fit.grigor.database_project.adapter.operation;

import ru.nsu.fit.grigor.database_project.adapter.operation.utility.dao.StatsDaoHelper;
import ru.nsu.fit.grigor.database_project.adapter.operation.utility.json.StatJsonHelper;
import ru.nsu.fit.grigor.database_project.model.port.IOHelper;
import ru.nsu.fit.grigor.database_project.model.port.Operation;
import ru.nsu.fit.grigor.database_project.model.port.dao.StatsDao;

import java.io.Reader;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

public class StatOperation implements Operation {
  private String startDate;
  private String endDate;
  private final StatJsonHelper jsonHelper = new StatJsonHelper();
  private final StatsDao daoHelper;

  public StatOperation() throws SQLException{
    try {
      daoHelper = new StatsDaoHelper();
    } catch (SQLException | ClassNotFoundException throwables) {
      throw new SQLException("could not connect to server");
    }
  }

  @Override
  public void makeOperation(IOHelper ioHelper) {
    try {
      String result = daoHelper.getCustomerStatsInJson(startDate, endDate);
      result = jsonHelper.getNiceJson(result);
      ioHelper.writeToOut(result);
    } catch (SQLException throwables) {
      ioHelper.writeToOut(jsonHelper.getJsonError(throwables.getMessage()));
    }
  }

  @Override
  public void setParameters(Reader reader) throws IllegalArgumentException {
    String error = "";
    Map<String, String> inputParams = jsonHelper.getInputParams(reader);

    if (inputParams.containsKey("startDate")) {
      startDate = inputParams.get("startDate");
      checkDate(startDate);
    } else {
      error += "missing startDate argument; ";
    }

    if (inputParams.containsKey("endDate")) {
      endDate = inputParams.get("endDate");
      checkDate(endDate);
    } else {
      error += "missing endDate argument;";
    }

    if (!error.isEmpty()) {
      throw new IllegalArgumentException(error);
    }
  }

  private void checkDate(String date) {
    String pattern = "yyyy-MM-dd";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    simpleDateFormat.setLenient(false);
    try {
      String parseDate = simpleDateFormat.format(simpleDateFormat.parse(date));
      if (!parseDate.equals(date)) {
        throw new IllegalArgumentException("wrong date format");
      }
    } catch (ParseException e) {
      throw new IllegalArgumentException("wrong date format");
    }
  }
}
