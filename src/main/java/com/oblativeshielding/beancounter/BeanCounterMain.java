/**@author Joseph Manno AKA OblativeShielding */
package com.oblativeshielding.beancounter;

import com.oblativeshielding.beancounter.budget.Budget;
import com.oblativeshielding.beancounter.category.Category;
import com.oblativeshielding.beancounter.category.Debt;
import com.oblativeshielding.beancounter.current.CurrentAllotments;
import com.oblativeshielding.beancounter.exception.InsufficientFundsException;
import com.oblativeshielding.beancounter.exception.NonexistentAllotmentException;
import com.oblativeshielding.beancounter.exception.NonexistentCategoryException;
import com.oblativeshielding.beancounter.history.History;
import com.oblativeshielding.beancounter.io.StatusPrinter;
import javafx.scene.paint.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;



/** The class responsible for managing program elements behind the scenes. */
public class BeanCounterMain {
  
  // active categories
  private final HashMap<String, Category> activeCategories;
  // available budgets
  private final ArrayList<Budget> availableBudgets;

  // currently loaded allotment of funds
  private final CurrentAllotments currentAllotments;
  // currently selected budget
  private final Budget currentBudget;
  // currently loaded history
  private final History currentHistory;
  // status printer
  //TODO remove this?
  private final StatusPrinter statusPrinter;

  // category ID counter
  //TODO replace this when ID system finished
  private int idCounter;

  /**
   * Constructor for BeanCounterMain.
   * 
   * For now, at least, this constructor starts from scratch
   */
  public BeanCounterMain() {

    // initialize stuff
    activeCategories = new HashMap<>();
    availableBudgets = new ArrayList<>();
    currentAllotments = new CurrentAllotments();
    currentBudget = new Budget("New Budget");
    currentHistory = new History();
    statusPrinter = new StatusPrinter(this);

    //TODO Remove when unneeded
    idCounter = 0;

  }

  /**
   * Adds a new category using provided information.
   */
  public void addNewCategory(String name, Color color) {

    //TODO make this more general
    String refId = name.substring(0, 3) + String.format("%02d", idCounter);
    while(activeCategories.containsKey(refId)) {
      idCounter++;
      refId = name.substring(0, 3) + String.format("%02d", idCounter);
    }
    idCounter = 0;
    Category newCategory = new Debt(name, color, refId);
    activeCategories.put(newCategory.getRefId(), newCategory);

  }

  //TODO make this more OCP compliant
  /**
   * Returns the category with the corresponding refId
   */
  public Category getCategory(String refId) throws NonexistentCategoryException {
    if(!activeCategories.containsKey(refId)) {
      throw new NonexistentCategoryException(refId);
    } else {
      return activeCategories.get(refId);
    }
  }

  //TODO make this more OCP compliant
  /**
   * Returns the category with the corresponding refId
   */
  public Category[] getCategories() {
    Category[] arr = new Category[activeCategories.size()];
    return activeCategories.values().toArray(arr);
  }

  /**
   * Returns an array of the reference IDs of the active categories
   */
  public String[] getCategoryRefIds() {
    String[] ids = new String[activeCategories.keySet().size()];
    ids = activeCategories.keySet().toArray(ids);
    return ids;
  }

  /**
   * Adds funds to the specified category's allotment.
   */
  public void addFunds(int amount, String refId) throws NonexistentCategoryException {

    //TODO is null acceptable?
    if(refId == null || activeCategories.containsKey(refId)) {
      currentAllotments.addFunds(amount, refId);
    } else {
      throw new NonexistentCategoryException("No category with ID " + refId + "exists.");
    }

  }

  /**
   * Returns the total funds contained within the allotments.
   */
  public int getAllotmentTotal() throws NonexistentAllotmentException {
    return currentAllotments.getTotal();
  }

  /**
   * Returns the amount allotted to the provided category
   */
  public int getAllotmentAmount(String refId) throws NonexistentAllotmentException {
    return currentAllotments.getAllotmentAmount(refId);
  }

  /**
   * Remove the specified amount from the specified category's allotment.
   * 
   * Eventually, this will also log the event in the history.
   */
  public void logExpense(int amount, String refId)
      throws InsufficientFundsException, NonexistentAllotmentException {

    currentAllotments.removeFunds(amount, refId);

  }

  /**
   * "Move" the specified amount from one allotment to another.
   */
  public void transfer(int amount, String sourceId, String destId) 
      throws InsufficientFundsException, NonexistentAllotmentException {

    currentAllotments.removeFunds(amount, sourceId);
    currentAllotments.addFunds(amount, destId);

  }

  /**
   * Tells the assigned status printer to print to the given filename.
   */
  public void printStatus(String fileName) throws IOException {
    statusPrinter.printStatus(fileName);
  }

}
