package ru.nsu.fit.grigor.database_project;

import ru.nsu.fit.grigor.database_project.adapter.JsonHelper;
import ru.nsu.fit.grigor.database_project.adapter.operation.OperationFactory;
import ru.nsu.fit.grigor.database_project.model.port.IOHelper;
import ru.nsu.fit.grigor.database_project.model.port.Operation;

import java.io.*;

public class ResultSeeker {
  private String inputFileName;
  private String outputFileName;
  private Operation.OperationType operationType;
  private IOHelper ioHelper;

  // todo: move those two to a single class
  private JsonHelper jsonHelper;

  public ResultSeeker(String inputFileName, String outputFileName, String operationType, IOHelper ioHelper) {
    jsonHelper = new JsonHelper();
    this.ioHelper = ioHelper;
    this.inputFileName = inputFileName;
    this.outputFileName = outputFileName;
    try {
      this.operationType = Operation.OperationType.valueOf(operationType);
    } catch (IllegalArgumentException e) {
      // todo: write the result TO JSON
    }
  }

  public void executeOperation() {//todo: rename
    // create the operation
    OperationFactory factory = new OperationFactory();
    Operation operation = factory.createOperation(operationType);

    // make operation
    try (Reader reader = new InputStreamReader(new FileInputStream(inputFileName))) {
      operation.setParameters(reader);
    } catch (IOException e) {
      //todo:
      ioHelper.writeErrorToOut("");
      e.printStackTrace();
    }

    try(Writer writer = new OutputStreamWriter(new FileOutputStream(outputFileName))) {
      operation.makeOperation(writer);
    } catch (IOException e) {
      //todo:
    }
  }

}
