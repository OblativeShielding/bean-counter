/**@author Joseph Manno AKA OblativeShielding */
package com.oblativeshielding.beancounter.current;

//TODO - Is this class entirely unnecessary?

import com.oblativeshielding.beancounter.category.Category;

/**
 * A single allotment of currently available funds.
 * 
 * At the time of writing, this class exists solely to store information.
 */
public class CurrentAllotment {
  
  /** The category that this allotment belongs to. */
  public Category category;
  /** The amount of funds in this allotment, in cents. */
  public int amount;

  /** Constructor for CurrentAllotment, setting values to those provided. */
  public CurrentAllotment(Category category, int amount) {

    // assign instance fields
    this.category = category;
    this.amount = amount;

  }

}
