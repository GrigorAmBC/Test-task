package ru.nsu.fit.grigor.database_project.adapter.operation;

import ru.nsu.fit.grigor.database_project.model.port.Operation;

import java.sql.SQLException;

public class OperationFactory {

  public static Operation createOperation(Operation.OperationType operationType) throws SQLException {
    Operation operation = null;
    if (operationType.equals(Operation.OperationType.search)) {
      operation = new SearchOperation();
    } else if (operationType.equals(Operation.OperationType.stat)) {
      operation = new StatOperation();
    }

    return operation;
  }
}
