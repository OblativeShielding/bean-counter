/**@author Joseph Manno AKA OblativeShielding */
package com.oblativeshielding.beancounter.history;

import com.oblativeshielding.beancounter.category.Category;
import java.util.Date;

/**
 * Contains information for a past expenditure.
 * 
 * This class exists solely for data storage.
 * At the time of writing, it is immutable.
 */
public class Expenditure {
  
  /** The date this expense occurred. */
  public final Date date;
  /** The category that this expenditure belongs to. */
  public final Category category; //TODO Switch to string identifier instead of reference?
  /** The amount of this expenditure (in cents). */
  public final int amount;
  /** A brief description of the expense, or other notes as desired. */
  public final String note;

  /**
   * Constructor method setting the fields to the values provided.
   */
  public Expenditure(Date date, Category category, int amount, String note) {

      // Assign instance fields
      this.date = date;
      this.category = category;
      this.amount = amount;
      this.note = note;

  }

}
