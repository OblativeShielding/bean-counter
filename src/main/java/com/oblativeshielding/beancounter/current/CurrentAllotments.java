/**@author Joseph Manno AKA OblativeShielding */
package com.oblativeshielding.beancounter.current;

import com.oblativeshielding.beancounter.exception.InsufficientFundsException;
import com.oblativeshielding.beancounter.exception.NonexistentAllotmentException;
import java.util.HashMap;

/**
 * Manages all active allotments of funds.
 * 
 * There should only be one instance of CurrentAllotments per instance of BeanCounterMain.
 */
public class CurrentAllotments {

  // list of allotments
  private final HashMap<String, Integer> allotments;

  /**
   * Constructor for CurrentAllotments (from scratch).
   */
  public CurrentAllotments() {

    allotments = new HashMap<>();

  }

  /**
   * Adds specified funds to the allotment for the given category.
   */
  public void addFunds(int amount, String categoryId) {

    if(allotments.get(categoryId) == null) {
      allotments.put(categoryId, amount);
    } else {
      allotments.put(categoryId, (allotments.get(categoryId) + amount));
    }

  }

  /**
   * Removes specified funds from the allotment for the given category.
   * 
   * @throws InsufficientFundsException when trying to remove more than is available
   * @throws NonexistentAllotmentException when trying to modify an exception that does not exist
   */
  public void removeFunds(int amount, String categoryId)
      throws InsufficientFundsException, NonexistentAllotmentException {

    if(!allotments.containsKey(categoryId)) {
      throw new NonexistentAllotmentException("No allotment exists with ID " + categoryId + ".");
    } else if(amount > allotments.get(categoryId)) {
      throw new InsufficientFundsException(categoryId + "does not have " + amount + " funds.");
    } else {
      allotments.put(categoryId, allotments.get(categoryId) - amount);
    }

  }

  /**
   * Calculates and returns the total of all allotments.
   * 
   * @return an integer sum of all allotments' amounts
   */
  public int getTotal() {

    int sum = 0;
    for(String key : allotments.keySet()) {
      sum += allotments.get(key);
    }
    return sum;

  }

  /**
   * Returns the value of a single allotment based on the category ID.
   * 
   * @return an int equal to the amount of the allotment requested, -1 if not found
   */
  public int getAllotmentAmount(String categoryId) {
    if(!allotments.containsKey(categoryId)) {
      return -1;
    } else {
      return allotments.get(categoryId);
    }

  }

  /**
   * Calculates the ratio of the specified allotment to the total amount of funds.
   * 
   * @return a double from 0.0 to 1.0 indicating the fraction of the whole, -1.0 if not found
   */
  public double getAllotmentFraction(String categoryId) {

    if(!allotments.containsKey(categoryId)) {
      return -1.0;
    } else {
      return (double) allotments.get(categoryId) / (double) getTotal();
    }

  }
  
}
