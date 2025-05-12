/**@author Joseph Manno AKA OblativeShielding */
package com.oblativeshielding.beancounter.budget;

import java.util.ArrayList;

/**
 * Abstract class for budget partitions, i.e. sub-budgets with their own points and subdivisions
 */
public abstract class BudgetPartition implements BudgetElement {
  
  private final ArrayList<BudgetElement> subElements;

  /**
   * Super constructor for child objects.
   */
  public BudgetPartition() {
    subElements = new ArrayList<>();
  }

  /**
   * Add the provided budget element to the list of sub-elements.
   */
  public void addElement(BudgetElement newElement) {
    //TODO prevent elements from being added to anything more than once?
    subElements.add(newElement);
  }

  //TODO return element categories and values somehow

  /**
   * Returns the total amount of funds within this partition that are allocated.
   * 
   * @param overallTotal an int indicating the total value of the partition this partition is in.
   * @return an int indicating number of cents allocated within this partition.
   */
  public int getTotalUsed(int overallTotal) {

    // total sum variable
    int usedTotal = 0;
    // total allowed value of this partition
    int partitionTotal = this.getValue(overallTotal);
    // iterate through sub elements and sum values
    for(BudgetElement element : subElements) {
      usedTotal += element.getValue(partitionTotal);
    }
    // return the sum
    return usedTotal;

  }

}
