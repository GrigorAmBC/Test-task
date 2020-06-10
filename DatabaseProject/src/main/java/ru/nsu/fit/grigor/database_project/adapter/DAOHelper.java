package ru.nsu.fit.grigor.database_project.adapter;

import ru.nsu.fit.grigor.database_project.model.entity.Customer;
import ru.nsu.fit.grigor.database_project.model.port.CustomerDAO;
import ru.nsu.fit.grigor.database_project.model.port.ProductDAO;
import ru.nsu.fit.grigor.database_project.model.port.PurchaseDAO;

import java.sql.*;
import java.util.Date;
import java.util.List;

public class DAOHelper implements CustomerDAO, PurchaseDAO, ProductDAO {
  private Connection connection;
  private Statement curStatement;
  private String url;
  private String login;
  private String password;

  public DAOHelper(String url, String login, String password) throws SQLException, ClassNotFoundException {
    this.login = login;
    this.password = password;
    this.url = url;
    setConnection();
  }

  public DAOHelper() throws SQLException, ClassNotFoundException {
    url = "jdbc:postgresql://localhost:5432/test-db";
    login = "postgres";
    password = "postgres";
    setConnection();
  }

  public void executeQuery(String query) {
    if (curStatement != null) {
      try {
        curStatement.close();
      } catch (SQLException e) {
        e.printStackTrace();//todo:
      }
    }

    try {
      Statement stmt = connection.createStatement();

      ResultSet rs = stmt.executeQuery(query);

      rs.close();
      stmt.close();
    } catch (Exception e) {
      e.printStackTrace();//todo:
    }
  }


  public void setConnection() throws ClassNotFoundException, SQLException {
    if (connection != null) {
      return;
    }
    Class.forName("org.postgresql.Driver");
    connection = DriverManager.getConnection(url, login, password);
  }

  public void o() {/*
    ResultSet rs = stmt.executeQuery("SELECT * FROM JC_CONTACT");
    while (rs.next()) {
      String str = rs.getString("contact_id")+":" +rs.getString(2);
      System.out.println("Contact:" + str);
    }
    rs.close();*/
  }

  @Override
  public List<Customer> getUniqueCustomers() {
    String query = "";

   /* String query = "SELECT DISTINCT column id FROM Customer;";
    try {
      Statement statement = connection.createStatement();
      ResultSet rs = statement.executeQuery(query);
      return rs;
    } catch (SQLException e) {
      e.printStackTrace();//todo
      return null;
    }
*/
    return null;
  }

  @Override
  public ResultSet getCustomerByLastName(String lastName) {//#1 search
    String query = "SELECT column first_name, column last_name FROM Customer WHERE last_name=" + lastName;
    try {
      Statement statement = connection.createStatement();
      ResultSet rs = statement.executeQuery(query);
      return rs;
    } catch (SQLException e) {
      e.printStackTrace();//todo
      return null;
    }
  }

  @Override
  public Customer getCustomerByName(String name) {
    /*String query = "SELECT DISTINCT column id FROM Customer;";
    try {
      Statement statement = connection.createStatement();
      ResultSet rs = statement.executeQuery(query);
      return rs;
    } catch (SQLException e) {
      e.printStackTrace();//todo
      return null;
    }
*/
    return null;
  }

  @Override
  public ResultSet getCustomersByPurchase(String productName, int minTimes) {
    String query = "SELECT DISTINCT column id FROM Customer;";
    try {
      Statement statement = connection.createStatement();
      ResultSet rs = statement.executeQuery(query);
      return rs;
    } catch (SQLException e) {
      e.printStackTrace();//todo
      return null;
    }
  }

  @Override
  public List<Customer> getCustomersByPurchaseVolume(double minPurchaseVolume, double maxPurchaseVolume) {
    String query = "SELECT DISTINCT column id FROM Customer;";
    try {
      Statement statement = connection.createStatement();
      ResultSet rs = statement.executeQuery(query);
      return rs;
    } catch (SQLException e) {
      e.printStackTrace();//todo
      return null;
    }

    return null;
  }

  @Override
  public List<Customer> getPassiveCustomers(int count) {
    String query = "SELECT DISTINCT column id FROM Customer;";
    try {
      Statement statement = connection.createStatement();
      ResultSet rs = statement.executeQuery(query);
      return rs;
    } catch (SQLException e) {
      e.printStackTrace();//todo
      return null;
    }

    return null;
  }

  @Override
  public void getCustomerPurchases() {
    String query = "SELECT DISTINCT column id FROM Customer;";
    try {
      Statement statement = connection.createStatement();
      ResultSet rs = statement.executeQuery(query);
      return rs;
    } catch (SQLException e) {
      e.printStackTrace();//todo
      return null;
    }

    return null;
  }

  @Override
  public void getCustomerPurchasesByDate(Customer customer, Date left, Date right) {
    String query = "SELECT DISTINCT column id FROM Customer;";
    try {
      Statement statement = connection.createStatement();
      ResultSet rs = statement.executeQuery(query);
      return rs;
    } catch (SQLException e) {
      e.printStackTrace();//todo
      return null;
    }

    return null;
  }
}
