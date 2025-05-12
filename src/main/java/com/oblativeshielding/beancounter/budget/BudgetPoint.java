/**@author OblativeShielding AKA Joseph Manno */
package com.oblativeshielding.beancounter.budget;

/**
 * Abstract class for budget points.
 */
public abstract class BudgetPoint implements BudgetElement {

  // ID of the category this budget point belongs to
  private String categoryId;

  /** Super constructor for BudgetPoint child objects. */
  public BudgetPoint(String categoryId) {
    this.categoryId = categoryId;
  }

  /** Returns the reference ID of the category this budget point belongs to. */
  public String getCategoryId() {
    return this.categoryId;
  }

  /** Sets the category reference ID for this budget point. */
  public void setCategoryId(String newId) {
    this.categoryId = newId;
  }
  
}
