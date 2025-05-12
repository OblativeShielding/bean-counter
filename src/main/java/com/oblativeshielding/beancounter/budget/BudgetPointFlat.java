/**@author Joseph Manno AKA OblativeShielding */
package com.oblativeshielding.beancounter.budget;

/**
 * Budget point that determines its value based on a flat dollar value.
 */
public class BudgetPointFlat extends BudgetPoint {
  
  private int amount;

  /**
   * Constructor for BudgetPointFlat, setting the amount to the int provided.
   */
  public BudgetPointFlat(String categoryId, int amount) {
    super(categoryId);
    this.amount = amount;
  }

  /**
   * Returns the amount allocated by this BudgetPoint, in this case just the value of amount.
   */
  @Override
  public int getValue(int total) {
    return amount;
  }

  /** Sets the amount that this budget point allocates. */
  public void setAmount(int amount) {
    this.amount = amount;
  }

  /**
   * Returns the amount that this budget point allocates.
   * 
   * At the time of writing, this is mostly redundant due to getValue.
   * However, the functionality of getValue may change, so this is for consistency's sake.
   */
  public int getAmount() {
    return amount;
  }

}
