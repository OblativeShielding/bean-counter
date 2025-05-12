/**@author Joseph Manno AKA OblativeShielding */
package com.oblativeshielding.beancounter.budget;

/**
 * Budget point that bases its value on a percentage.
 */
public class BudgetPointPercent extends BudgetPoint {

  // what percentage this budget point uses - should be between 0 and 1
  private double percentage;

  /**
   * Constructor for BudgetPointPercent, setting the percentage to the double provided.
   * 
   * @param percentage a double between 0 and 1 indicating what percentage this uses.
   */
  public BudgetPointPercent(String categoryId, double percentage) {
    //TODO Check for validity of percentage?
    super(categoryId);
    this.percentage = percentage;
  }

  /**
   * Returns the amount allocated by this BudgetPoint, in this case the percentage of total floored.
   */
  @Override
  public int getValue(int total) {
    return (int) (total * percentage);
  }

  /** Sets the percentage that this budget point uses. */
  public void setPercentage(double percentage) {
    this.percentage = percentage;
  }

  /** Returns the percentage that this budget point uses to allocate. */
  public double getPercentage() {
    return percentage;
  }
  
}