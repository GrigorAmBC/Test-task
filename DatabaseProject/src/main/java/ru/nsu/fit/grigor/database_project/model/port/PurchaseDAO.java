package ru.nsu.fit.grigor.database_project.model.port;

import ru.nsu.fit.grigor.database_project.model.entity.Customer;

import java.util.Date;

public interface PurchaseDAO {
  void getCustomerPurchases();// todo:need?
  void getCustomerPurchasesByDate(Customer customer, Date left, Date right);
}
