/**@author Joseph Manno AKA OblativeShielding */
package com.oblativeshielding.beancounter;

import com.oblativeshielding.beancounter.budget.Budget;
import com.oblativeshielding.beancounter.category.Category;
import com.oblativeshielding.beancounter.category.Debt;
import com.oblativeshielding.beancounter.current.CurrentAllotments;
import com.oblativeshielding.beancounter.exception.NonexistentCategoryException;
import com.oblativeshielding.beancounter.history.History;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.HashMap;


/** The class responsible for managing program elements behind the scenes. */
public class BeanCounterMain {
  
  // active categories
  private HashMap<String, Category> activeCategories;
  // available budgets
  private ArrayList<Budget> availableBudgets;

  // currently loaded allotment of funds
  private CurrentAllotments currentAllotments;
  // currently selected budget
  private Budget currentBudget;
  // currently loaded history
  private History currentHistory;

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
    currentBudget = new Budget();
    currentHistory = new History();

    //TODO Remove when unneeded
    idCounter = 0;

  }

  /**
   * Adds a new category using provided information.
   */
  public void addNewCategory(String name, Color color) {

    //TODO make this more general
    Category newCategory = new Debt(name, color,
        (name.substring(0, 3) + String.format("%02d", idCounter)));
    activeCategories.put(newCategory.getRefId(), newCategory);

  }

  /**
   * Adds funds to the specified category's allotment.
   */
  public void addFunds(int amount, String refId) throws NonexistentCategoryException {

    if(refId == null || activeCategories.containsKey(refId)) {
      currentAllotments.addFunds(amount, refId);
    } else {
      throw new NonexistentCategoryException("No category with ID " + refId + "exists.");
    }

  }

}
