package ru.nsu.fit.grigor.database_project.adapter.operation.criteria;

import com.google.gson.annotations.Expose;
import ru.nsu.fit.grigor.database_project.adapter.operation.utility.json.SearchJsonHelper;
import ru.nsu.fit.grigor.database_project.model.port.Criteria;
import ru.nsu.fit.grigor.database_project.model.port.dao.SearchDao;

import java.sql.SQLException;

public class MinExpensesCriteria implements Criteria {
  @Expose
  private final int minExpenses;
  @Expose
  private final int maxExpenses;

  public MinExpensesCriteria(int minExpenses, int maxExpenses) {
    this.minExpenses = minExpenses;
    this.maxExpenses = maxExpenses;
  }

  @Override
  public void putCriteriaResult(SearchDao daoHelper, SearchJsonHelper jsonHelper) {
    if (checkParameters()) {
      jsonHelper.putCriteriaErrorMessage("wrong criteria arguments", this);
      return;
    }

    try {
      String jsonArray = daoHelper.getCustomersByPurchaseVolumeInJson(minExpenses, maxExpenses);
      jsonHelper.addCriteriaResult(jsonArray, this);
    } catch (SQLException throwables) {
      jsonHelper.putCriteriaErrorMessage("could not access database", this);
    }
  }

  private boolean checkParameters() {
    return minExpenses < 0 || maxExpenses < 0 || minExpenses > maxExpenses;
  }
}
