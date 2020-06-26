package ru.nsu.fit.grigor.database_project;

import ru.nsu.fit.grigor.database_project.adapter.FileIOHelper;

public class Main {
  public static void main(String[] args) {
    if (args.length != 3) {
      System.out.println("too few command line arguments");
    } else {
      ResultSeeker resultSeeker = new ResultSeeker(args[0], new FileIOHelper(args[1], args[2]));
    }
  }
}
