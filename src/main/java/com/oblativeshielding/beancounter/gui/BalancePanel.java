/**@author Joseph Manno AKA OblativeShielding */
package com.oblativeshielding.beancounter.gui;

import com.oblativeshielding.beancounter.BeanCounterMain;
import com.oblativeshielding.beancounter.category.Category;
import com.oblativeshielding.beancounter.exception.NonexistentAllotmentException;
import com.oblativeshielding.beancounter.exception.NonexistentCategoryException;

import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * Custom JavaFX node to handle display of balance information.
 */
public class BalancePanel extends SubPanel {

  // main program that this panel should refer to
  BeanCounterMain main;

  // label displaying total available funds
  Label totalLabel;
  // pie chart for displaying available funds
  PieChart balanceChart;
  // grid pane displaying allotments as list
  GridPane allotmentList;

  /**
   * BalancePanel constructor setting up initial stuff
   */
  public BalancePanel() {

    totalLabel = new Label();
    balanceChart = new PieChart();
    allotmentList = new GridPane();

    VBox v = new VBox();
    this.setContent(v);
    v.getChildren().add(totalLabel);
    v.getChildren().add(balanceChart);
    v.getChildren().add(allotmentList);

  }

  /**
   * Sets the BeanCounterMain that this panel should refer to.
   * 
   * This must be called at least once before other methods.
   */
  @Override
  public void setMain(BeanCounterMain main) {
    this.main = main;
  }

  /**
   * Re-renders the GUI visuals (presumably based on updated information).
   */
  @Override
  public void redraw() {

    // update the total label
    totalLabel.setText("Total Funds Available: " + dollarFormat(main.getAllotmentTotal()));

    //reset the pir chart and grid pane
    balanceChart.getData().clear();
    allotmentList.getChildren().clear();

    // iterate through active categories
    int rowCounter = 0;
    for(String refId : main.getCategoryRefIds()) {
      // attempt to retrieve allotment amount
      int amount;
      try {
        amount = main.getAllotmentAmount(refId);
      } catch (NonexistentAllotmentException e) {
        continue;
      }
      // if successful, add the category to the display
      Category c;
      try {
        c = main.getCategory(refId);
      } catch(NonexistentCategoryException e) {
        //TODO Do something else
        continue;
      }

      balanceChart.getData().add(new PieChart.Data(c.getName(), amount));
      allotmentList.add(new Label(c.getName()), 1, rowCounter);
      allotmentList.add(new Label(dollarFormat(amount)), 2, rowCounter);

      rowCounter++;

    }

    // after iteration, add unallocated funds
    try {
      int amount = main.getAllotmentAmount(null);
      balanceChart.getData().add(new PieChart.Data("Unallocated", amount));
      allotmentList.add(new Label("Unallocated"), 1, rowCounter);
      allotmentList.add(new Label(dollarFormat(amount)), 2, rowCounter);
    } catch (NonexistentAllotmentException e) {
      //TODO Do something?
    }

  }
  
}
