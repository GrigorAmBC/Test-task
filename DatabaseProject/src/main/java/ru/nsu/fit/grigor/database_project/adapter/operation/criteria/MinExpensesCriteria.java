package ru.nsu.fit.grigor.database_project.adapter.operation.criteria;

import ru.nsu.fit.grigor.database_project.adapter.DAOHelper;
import ru.nsu.fit.grigor.database_project.adapter.JsonHelper;
import ru.nsu.fit.grigor.database_project.adapter.JsonResult;
import ru.nsu.fit.grigor.database_project.model.port.Criteria;
import ru.nsu.fit.grigor.database_project.model.port.DAOHelper2;

import java.util.Map;

public class MinExpensesCriteria implements Criteria {
  int minExpenses;
  int maxExpenses;

  public MinExpensesCriteria(Map<String, String> parameters) {

  }

  @Override
  public JsonResult getCriteriaResult(DAOHelper daoHelper, JsonHelper jsonHelper) {
    return null;
  }
}
