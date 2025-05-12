/**@author Joseph Manno AKA OblativeShielding */
package com.oblativeshielding.beancounter.budget;

/**
 * Budget partition that determines its value based on a percentage of the whole.
 */
public class BudgetPartitionFlat extends BudgetPartition {
  
  // the amount of money (in cents) that this partition reserves for its subelements
  private int amount;

  /**
   * Constructor setting percentage to the double provided.
   */
  public BudgetPartitionFlat(int amount) {
    this.amount = amount;
  }

  /**
   * Returns the amount of money that this BudgetPartition reserves for its subelements.
   * 
   * @param total the total value available to this partition's "parent" in cents.
   * @return an int indicating the value of this partition in cents.
   */
  @Override
  public int getValue(int total) {
    return amount;
  }

  /** Sets the current value of amount. */
  public void setAmount(int amount) {
    this.amount = amount;
  }

  /**
   * Returns the current value of amount.
   * 
   * Currently redundant due to getValue.
   */
  public int getAmount() {
    return amount;
  }

}
