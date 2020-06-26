package ru.nsu.fit.grigor.database_project.model.port;

import ru.nsu.fit.grigor.database_project.adapter.operation.utility.json.SearchJsonHelper;
import ru.nsu.fit.grigor.database_project.model.port.dao.SearchDao;

public interface Criteria {
  void putCriteriaResult(SearchDao daoHelper, SearchJsonHelper searchJsonHelper);
}
