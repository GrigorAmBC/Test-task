package ru.nsu.fit.grigor.database_project.model.port;

import ru.nsu.fit.grigor.database_project.model.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO {

  List<Customer> getUniqueCustomers() throws SQLException;
  ResultSet getCustomerByLastName(String lastName);
  Customer getCustomerByName(String name);
  ResultSet getCustomersByPurchase(String productName, int minTimes);
  List<Customer> getCustomersByPurchaseVolume(double minPurchaseVolume, double maxPurchaseVolume);
  List<Customer> getPassiveCustomers(int count);
}