package ru.nsu.fit.grigor.database_project.model.port;

import ru.nsu.fit.grigor.database_project.adapter.DAOHelper;
import ru.nsu.fit.grigor.database_project.adapter.JsonHelper;
import ru.nsu.fit.grigor.database_project.adapter.JsonResult;

public interface Criteria {//todo: maybe add 'getCriteriaMap()?'
  JsonResult getCriteriaResult(DAOHelper daoHelper, JsonHelper jsonHelper);
}
