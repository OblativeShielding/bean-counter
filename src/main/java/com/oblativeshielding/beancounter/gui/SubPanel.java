/**@author Joseph Manno AKA OblativeShielding */
package com.oblativeshielding.beancounter.gui;

import com.oblativeshielding.beancounter.BeanCounterMain;

import javafx.scene.control.ScrollPane;

/**
 * Abstract class for interior GUI panels
 */
public abstract class SubPanel extends ScrollPane {

  // money format string
  private static final String MONEY_FORMAT = "$%,d.%02d";

  /**
   * Sets the BeanCounterMain that this panel should refer to.
   * 
   * This must be called at least once before other methods.
   */
  public abstract void setMain(BeanCounterMain main);

  /**
   * Re-renders the GUI visuals (presumably based on updated information).
   */
  public abstract void redraw();

  /**
   * Formats the provided integer as a dollar string.
   */
  protected String dollarFormat(int cents) {
    return String.format(MONEY_FORMAT, cents/100, cents%100);
  }
  
}
