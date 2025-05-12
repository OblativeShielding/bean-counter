/**@author Joseph Manno AKA OblativeShielding */
package com.oblativeshielding.beancounter.budget;

/**
 * Budget partition that bases its value on a percentage.
 */
public class BudgetPartitionPercent extends BudgetPartition {

  // percentage of total that this budget partition uses - should be between 0 and 1
  private double percentage;

  /**
   * Constructor setting percentage to the double provided.
   */
  public BudgetPartitionPercent(double percentage) {
    //TODO check for valid percentage?
    this.percentage = percentage;
  }

  /**
   * Returns the amount of money that this BudgetPartition reserves for its subelements.
   * 
   * @param total the total value available to this partition's "parent" in cents.
   * @return an int indicating the value of this partition in cents, floored.
   */
  @Override
  public int getValue(int total) {
    return (int) (total * percentage);
  }

  /** Sets the current value of percentage. */
  public void setPercentage(double percentage) {
    this.percentage = percentage;
  }

  /** Returns the current value of percentage. */
  public double getPercentage() {
    return percentage;
  }
  
}
