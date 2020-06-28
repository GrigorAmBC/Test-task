package ru.nsu.fit.grigor.database_project.adapter.operation.utility.dao;

import ru.nsu.fit.grigor.database_project.model.port.dao.StatsDao;

import java.io.Closeable;
import java.sql.*;

public class StatsDaoHelper implements StatsDao, Closeable {

  private Connection connection;
  private Statement curStatement;
  private String url = "jdbc:postgresql://localhost:5432/shop";
  private String login = "postgres";
  private String password = "postgres";

  public StatsDaoHelper(String url, String login, String password) throws SQLException, ClassNotFoundException {
    this.login = login;
    this.password = password;
    this.url = url;
    setConnection();
  }

  public StatsDaoHelper() throws SQLException, ClassNotFoundException {
    setConnection();
    setStatement();
  }


  public void setConnection() throws ClassNotFoundException, SQLException {
    Class.forName("org.postgresql.Driver");
    connection = DriverManager.getConnection(url, login, password);
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
  public void close() {
    try {
      curStatement.close();
      connection.close();
    } catch (SQLException ignore) {
    }
  }
}
