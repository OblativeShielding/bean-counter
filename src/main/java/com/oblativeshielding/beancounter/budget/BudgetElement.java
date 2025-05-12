/**@author Joseph Manno AKA OblativeShielding */
package com.oblativeshielding.beancounter.budget;

/**
 * Interface for interacting with budget elements
 */
public interface BudgetElement {

  /** Returns the amount of money that this element is worth given the provided total.
   * 
   * @param total an int indicating the total funds available in the parent element's partition
   * @return an int indicating the amount of money (in cents) this element is worth
   */
  public abstract int getValue(int total);
  
}
