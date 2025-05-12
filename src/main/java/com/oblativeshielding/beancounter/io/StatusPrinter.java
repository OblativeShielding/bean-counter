/**@author Joseph Manno AKA OblativeShielding */
package com.oblativeshielding.beancounter.io;

import com.oblativeshielding.beancounter.BeanCounterMain;
import com.oblativeshielding.beancounter.category.Category;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * I/O class to print the current allotments to a file.
 */
public class StatusPrinter {
  
  private final BeanCounterMain main;

  private final String LINE_FORMAT = "%-30s $%20d.%02d\n";

  /**
   * Constructor for StatusPrinter, assigning 
   */
  public StatusPrinter(BeanCounterMain main) {

    this.main = main;

  }

  /**
   * Prints the current status to the file at the given filename.
   */
  @SuppressWarnings("ConvertToTryWithResources")
  public void printStatus(String fileName) throws IOException {

    File file = new File(fileName);
    BufferedWriter writer = new BufferedWriter(new FileWriter(file));

    writer.write(String.format("%-30s  %22s\n", "Category", "Allotted Amount"));

    for(Category c : main.getCategories()) {
      writer.write(String.format(LINE_FORMAT,
          c.getName(),
          main.getAllotmentAmount(c.getRefId()) / 100,
          main.getAllotmentAmount(c.getRefId()) % 100));
    }

    writer.write(String.format(LINE_FORMAT,
          "Unallocated",
          main.getAllotmentAmount(null) / 100,
          main.getAllotmentAmount(null) % 100));

    writer.close();

  }

}
