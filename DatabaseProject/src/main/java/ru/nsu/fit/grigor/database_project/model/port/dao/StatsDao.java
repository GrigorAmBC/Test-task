package ru.nsu.fit.grigor.database_project.model.port.dao;

import java.sql.SQLException;

public interface StatsDao {
  String getCustomerStatsInJson(String leftDate, String rightDate) throws SQLException;
}
