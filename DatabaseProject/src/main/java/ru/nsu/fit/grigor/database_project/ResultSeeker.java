package ru.nsu.fit.grigor.database_project;

import ru.nsu.fit.grigor.database_project.adapter.operation.OperationFactory;
import ru.nsu.fit.grigor.database_project.adapter.operation.utility.json.JsonHelper;
import ru.nsu.fit.grigor.database_project.model.port.IOHelper;
import ru.nsu.fit.grigor.database_project.model.port.Operation;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;

public class ResultSeeker {
  private final IOHelper ioHelper;
  private final JsonHelper jsonHelper = new JsonHelper();

  public ResultSeeker(String operationType, IOHelper ioHelper) {
    this.ioHelper = ioHelper;
    Operation.OperationType type;
    try {
      type = Operation.OperationType.valueOf(operationType);
      executeOperation(type);
    } catch (IllegalArgumentException e) {
      ioHelper.writeToOut(jsonHelper
              .getJsonError("no such operation: \""+ operationType + "\""));
    }
  }

  public void executeOperation(Operation.OperationType operationType) {
    Operation operation;

    try {
      operation = OperationFactory.createOperation(operationType);
    } catch (SQLException throwables) {
      ioHelper.writeToOut(jsonHelper.getJsonError(throwables.getMessage()));
      return;
    }

    boolean readOk = false;
    try (Reader reader = ioHelper.getInputReader()) {
      operation.setParameters(reader);
      readOk = true;
    } catch (IOException e) {
      ioHelper.writeToOut(jsonHelper.getJsonError("could not open input file"));
    } catch (IllegalArgumentException e) {
      ioHelper.writeToOut(jsonHelper.getJsonError(e.getMessage()));
    }

    if (readOk) {
      operation.makeOperation(ioHelper);
    }
  }

}
