package ru.nsu.fit.grigor.database_project.adapter.operation;

import ru.nsu.fit.grigor.database_project.model.port.Operation;

public class OperationFactory {

  public Operation createOperation(Operation.OperationType operationType) {
    Operation operation = null;
    if (Operation.OperationType.valueOf("search") == operationType) {
      operation = new SearchOperation();
    } else if (Operation.OperationType.valueOf("stat") == operationType) {
      operation = new StatOperation();
    } else {
      //todo: throw exception
    }

    return operation;
  }
}
