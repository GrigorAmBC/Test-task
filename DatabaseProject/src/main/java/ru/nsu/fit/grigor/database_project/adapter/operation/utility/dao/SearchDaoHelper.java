package ru.nsu.fit.grigor.database_project.adapter.operation.utility.dao;

import ru.nsu.fit.grigor.database_project.model.port.dao.SearchDao;

import java.io.Closeable;
import java.sql.*;

public class SearchDaoHelper implements SearchDao, Closeable {
  private Connection connection;
  private Statement curStatement;

  public SearchDaoHelper() throws SQLException, ClassNotFoundException {
    setConnection();
    setStatement();
  }


  private void setConnection() throws ClassNotFoundException, SQLException {
    Class.forName("org.postgresql.Driver");
    String url = "jdbc:postgresql://localhost:5432/shop";
    String login = "postgres";
    String password = "postgres";
    connection = DriverManager.getConnection(url, login, password);
  }

  private void setStatement() throws SQLException {
    curStatement = connection.createStatement();
  }

  @Override
  public String getCustomerByLastNameInJson(String lastName) throws SQLException {//todo: add checks
    String query = "select array_to_json(array_agg(row_to_json(t))) from (select distinct c.last_name as \"lastName\" , " +
            "c.first_name  as \"firstName\"from \"Customer\" c\n" +
            "\tjoin \"Sale\" s on \n" +
            "\tc.customer_id = s.customer_id\n" +
            "\twhere c.last_name = '" + lastName + "') t";

    ResultSet rs = curStatement.executeQuery(query);
    rs.next();
    return rs.getString(1);
  }

  @Override
  public String getCustomersByPurchaseInJson(String productName, int minTimes) throws SQLException {//todo: add checks
    String query = "select array_to_json(array_agg(row_to_json(t))) from (select last_name as \"lastName\", first_name as \"firstName\"from (select c.first_name, c.last_name, p.product_name,\n" +
            "sum(sd.quantity) as OverallQuantity from \n" +
            "\"Customer\" c join \"Sale\" s on \n" +
            "c.customer_id = s.customer_id\n" +
            "join \"SaleDetail\" sd on\n" +
            "s.sale_id = sd.sale_id\n" +
            "join \"Product\" p on\n" +
            "p.product_id = sd.product_id\n" +
            "group by c.first_name, c.last_name, p.product_name) q1\n" +
            "where OverallQuantity >= " + minTimes + " and product_name = '" + productName + "') t";

    ResultSet rs = curStatement.executeQuery(query);
    rs.next();
    return rs.getString(1);
  }

  @Override
  public String getCustomersByPurchaseVolumeInJson(double minPurchaseVolume, double maxPurchaseVolume) throws SQLException {//todo: add checks
    String query = "select array_to_json(array_agg(row_to_json(t))) from " +
            "(select last_name as \"lastName\", first_name as \"firstName\" from " +
            "(select c.first_name, c.last_name, \n" +
            "sum(sd.quantity*p.product_price) as TotalExpenses from \n" +
            "\"Customer\" c join \"Sale\" s on \n" +
            "c.customer_id = s.customer_id\n" +
            "join \"SaleDetail\" sd on\n" +
            "s.sale_id = sd.sale_id\n" +
            "join \"Product\" p on\n" +
            "p.product_id = sd.product_id\n" +
            "group by c.first_name, c.last_name) q1\n" +
            "where TotalExpenses between " + minPurchaseVolume + " and " + maxPurchaseVolume + ") t";

    ResultSet rs = curStatement.executeQuery(query);
    rs.next();
    return rs.getString(1);
  }

  @Override
  public String getPassiveCustomersInJson(int count) throws SQLException {//todo: add checks
    String query = "select array_to_json(array_agg(row_to_json(t))) from (select last_name as \"lastName\", first_name as \"firstName\" from (select c.first_name, c.last_name,\n" +
            "SUM(sd.quantity) as OverallQuantity from \n" +
            "\"Customer\" c join \"Sale\" s on \n" +
            "c.customer_id = s.customer_id\n" +
            "join \"SaleDetail\" sd on\n" +
            "s.sale_id = sd.sale_id\n" +
            "group by c.first_name, c.last_name) q1\n" +
            "order by OverallQuantity asc\n" +
            "limit " + count + ") t";


    ResultSet rs = curStatement.executeQuery(query);
    rs.next();
    return rs.getString(1);
  }

  @Override
  public void close() {
    try {
      curStatement.close();
      connection.close();
    } catch (SQLException ignore) {
    }
  }
}
