/**@author Joseph Manno AKA OblativeShielding */
package com.oblativeshielding.beancounter.budget;

import java.util.ArrayList;

/**
 * Stores and manages a single budget.
 */
public class Budget {
  
  // list of budget elements
  private final ArrayList<BudgetElement> elements;
  // display name
  private String displayName;

  /**
   * Create a new budget with the given display name.
   */
  public Budget(String budgetName) {

    this.elements = new ArrayList<>();
    this.displayName = budgetName;

  }

  /**
   * Add an element to the budget.
   */
  public void addElement(BudgetElement newElement) {
    //TODO add some overflow check
    elements.add(newElement);
  }

  /** Gets display name. */
  public String getDisplayName() {
    return displayName;
  }

  /** Sets display name. */
  public void setDisplayName(String name) {
    displayName = name;
  }

}
