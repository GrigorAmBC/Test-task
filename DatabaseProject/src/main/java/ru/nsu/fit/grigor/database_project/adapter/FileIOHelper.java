package ru.nsu.fit.grigor.database_project.adapter;

import ru.nsu.fit.grigor.database_project.model.port.IOHelper;

import java.io.*;

public class FileIOHelper implements IOHelper {
  private final String inFileName;
  private final String outFileName;

  public FileIOHelper(String inFileName, String outFileName) {
    this.inFileName = inFileName;
    this.outFileName = outFileName;
  }

  @Override
  public Reader getInputReader() throws FileNotFoundException {
    return new InputStreamReader(new FileInputStream(inFileName));
  }

  @Override
  public void writeToOut(String content) {
    try (Writer writer = new OutputStreamWriter(new FileOutputStream(outFileName))) {
      writer.write(content);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
