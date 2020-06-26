package ru.nsu.fit.grigor.database_project.adapter.operation.criteria;

import com.google.gson.annotations.Expose;
import ru.nsu.fit.grigor.database_project.adapter.operation.utility.json.SearchJsonHelper;
import ru.nsu.fit.grigor.database_project.model.port.Criteria;
import ru.nsu.fit.grigor.database_project.model.port.dao.SearchDao;

import java.sql.SQLException;

public class LastNameCriteria implements Criteria {
  @Expose
  private final String lastName;

  public LastNameCriteria(String lastName) {
    this.lastName = lastName;
  }

  @Override
  public void putCriteriaResult(SearchDao daoHelper, SearchJsonHelper jsonHelper) {
    if (checkParameters()) {
      jsonHelper.putCriteriaErrorMessage("wrong criteria arguments", this);
      return;
    }

    try {
      String jsonArray = daoHelper.getCustomerByLastNameInJson(lastName);
      jsonHelper.addCriteriaResult(jsonArray, this);
    } catch (SQLException throwables) {
      jsonHelper.putCriteriaErrorMessage("could not access database", this);
    }
  }

  private boolean checkParameters() {
    return lastName == null || lastName.isEmpty();
  }
}
