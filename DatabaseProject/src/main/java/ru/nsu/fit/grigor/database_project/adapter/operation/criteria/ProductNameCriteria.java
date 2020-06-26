package ru.nsu.fit.grigor.database_project.adapter.operation.criteria;

import com.google.gson.annotations.Expose;
import ru.nsu.fit.grigor.database_project.adapter.operation.utility.json.SearchJsonHelper;
import ru.nsu.fit.grigor.database_project.model.port.Criteria;
import ru.nsu.fit.grigor.database_project.model.port.dao.SearchDao;

import java.sql.SQLException;

public class ProductNameCriteria implements Criteria {
  @Expose
  private final String productName;
  @Expose
  private final int minTimes;

  public ProductNameCriteria(String productName, int minTimes) {
    this.productName = productName;
    this.minTimes = minTimes;
  }

  @Override
  public void putCriteriaResult(SearchDao daoHelper, SearchJsonHelper jsonHelper) {
    if (checkParameters()) {
      jsonHelper.putCriteriaErrorMessage("wrong criteria arguments", this);
      return;
    }

    try {
      String jsonArray = daoHelper.getCustomersByPurchaseInJson(productName, minTimes);
      jsonHelper.addCriteriaResult(jsonArray, this);
    } catch (SQLException throwables) {
      jsonHelper.putCriteriaErrorMessage("could not access database", this);
    }
  }

  private boolean checkParameters() {
    return  (productName == null || minTimes < 0);
  }
}
