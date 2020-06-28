package ru.nsu.fit.grigor.database_project.model.port.dao;

import java.sql.SQLException;

public interface SearchDao {
  String getCustomerByLastNameInJson(String lastName) throws SQLException;
  String getCustomersByPurchaseInJson(String productName, int minTimes) throws SQLException;
  String getCustomersByPurchaseVolumeInJson(double minPurchaseVolume, double maxPurchaseVolume) throws SQLException;
  String getPassiveCustomersInJson(int count) throws SQLException;
  void closeConnection();
}
