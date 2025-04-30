/**@author Joseph Manno AKA OblativeShielding */
package com.oblativeshielding.beancounter.history;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Manages expenditure history.
 */
public class History {
  
  // list of all expenditures
  private ArrayList<Expenditure> expenditures;

  /**
   * Constructor method to initialize a brand new history.
   */
  public History() {

    expenditures = new ArrayList<>();

  }

  /**
   * Constructor method to load history from the file provided.
   */
  public History(File file) throws IOException {

  }

  /**
   * Sorts the list of expenditures by date.
   */
  public void sortByDate() {
    expenditures.sort(Comparator.comparing(e -> e.date));
  }

  /**
   * Sorts the list of expenditures by category.
   */
  public void sortByCategory() {
    expenditures.sort(Comparator.comparing(e -> e.category));
  }

  /**
   * Sorts the list of expenditures by amount.
   */
  public void sortByAmount() {
    expenditures.sort(Comparator.comparing(e -> e.amount));
  }

  /**
   * Sorts the list of expenditures in alphabetical order by their notes.
   */
  public void sortByNote() {
    expenditures.sort(Comparator.comparing(e -> e.note));
  }

}
