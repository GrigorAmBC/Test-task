package ru.nsu.fit.grigor.database_project.adapter.operation.utility.dao;

import ru.nsu.fit.grigor.database_project.model.port.dao.StatsDao;

import java.sql.*;

public class StatsDaoHelper implements StatsDao {

  private Connection connection;
  private Statement curStatement;

  public StatsDaoHelper() throws SQLException {
    setConnection();
    setStatement();
  }

  public void setConnection() throws SQLException {
    try {
      Class.forName("org.postgresql.Driver");
      String url = "jdbc:postgresql://localhost:5432/shop";
      String login = "postgres";
      String password = "postgres";
      connection = DriverManager.getConnection(url, login, password);
    } catch (SQLException | ClassNotFoundException e) {
      throw new SQLException("could not connect to server");
    }
  }

  private void setStatement() throws SQLException {
    curStatement = connection.createStatement();
  }

  @Override
  public String getCustomerStatsInJson(String startDate, String endDate) throws SQLException {
    String query = "select json_build_object('type', 'stat',\n" +
            "'totalDays', (select count(*)\n" +
            "from generate_series(timestamp '" + startDate + "',\n" +
            "timestamp '" + endDate + "',\n" +
            "interval  '1 day') the_day\n" +
            "where  extract('ISODOW' from the_day) < 6),\n" +
            "'customers', json_agg(json_build_object('name', u.\"fullName\",\n" +
            "'purchases', u.\"purchases\",\n" +
            "'totalExpenses', u.\"totalExpenses\")),\n" +
            "'totalExpenses', sum(\"totalExpenses\"),\n" +
            "'avgExpenses', round(sum(\"totalExpenses\") / count(*), 2))\n" +
            "from (select \"fullName\", \n" +
            "json_agg(json_build_object(\n" +
            "'productName', \"productName\",\n" +
            "'expenses', \"totalExpenses\")) as \"purchases\",\n" +
            "sum(\"totalExpenses\") as \"totalExpenses\" \n" +
            "from (select c.customer_id as \"customerId\",\n" +
            "concat(c.last_name, ' ', c.first_name) as \"fullName\",\n" +
            "p.product_name as \"productName\",\n" +
            "sum(sd.quantity*p.product_price) as \"totalExpenses\"\n" +
            "from \"Customer\" c join \"Sale\" s on \n" +
            "c.customer_id = s.customer_id\n" +
            "join \"SaleDetail\" sd on\n" +
            "s.sale_id = sd.sale_id\n" +
            "join \"Product\" p on\n" +
            "p.product_id = sd.product_id\n" +
            "where s.order_date between '" + startDate + "' and '" + endDate + "'\n" +
            "and extract('ISODOW' from s.order_date ) < 6\n" +
            "group by c.customer_id, c.first_name, c.last_name, p.product_name\n" +
            "order by c.customer_id ASC) t\n" +
            "group by \"customerId\",\"fullName\") u";

    ResultSet rs = curStatement.executeQuery(query);
    rs.next();
    return rs.getString(1);
  }

  @Override
  public void closeConnection() {
    try {
      connection.close();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }
}
