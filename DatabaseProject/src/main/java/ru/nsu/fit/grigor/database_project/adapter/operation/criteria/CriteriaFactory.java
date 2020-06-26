package ru.nsu.fit.grigor.database_project.adapter.operation.criteria;

public class CriteriaFactory {
  public static Class<?> getCriteriaClass(String criteriaName) throws IllegalArgumentException {
    switch (criteriaName) {
      case "lastName":
        return LastNameCriteria.class;
      case "productName":
        return ProductNameCriteria.class;
      case "minExpenses":
        return MinExpensesCriteria.class;
      case "badCustomers":
        return BadCustomersCriteria.class;
      default:
        throw new IllegalArgumentException("no such criteria");
    }
  }
}
