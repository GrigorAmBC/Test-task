package ru.nsu.fit.grigor.database_project.adapter.operation;

import com.google.gson.JsonSyntaxException;
import ru.nsu.fit.grigor.database_project.adapter.operation.utility.dao.SearchDaoHelper;
import ru.nsu.fit.grigor.database_project.adapter.operation.utility.json.SearchJsonHelper;
import ru.nsu.fit.grigor.database_project.model.port.Criteria;
import ru.nsu.fit.grigor.database_project.model.port.IOHelper;
import ru.nsu.fit.grigor.database_project.model.port.Operation;
import ru.nsu.fit.grigor.database_project.model.port.dao.SearchDao;

import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchOperation implements Operation {
  private final SearchJsonHelper jsonHelper = new SearchJsonHelper();
  private final SearchDao daoHelper;
  private final List<Criteria> criteriaList = new ArrayList<>();

  public SearchOperation() throws SQLException {
    daoHelper = new SearchDaoHelper();
  }

  @Override
  public void makeOperation(IOHelper ioHelper) {
    for (Criteria criteria : criteriaList) {
      criteria.putCriteriaResult(daoHelper, jsonHelper);
    }
    ioHelper.writeToOut(jsonHelper.getJsonResult());
  }

  @Override
  public void setParameters(Reader reader) throws IllegalArgumentException, JsonSyntaxException {
    jsonHelper.setupCriteriaList(reader, criteriaList);
  }
}
