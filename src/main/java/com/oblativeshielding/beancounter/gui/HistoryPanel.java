/**@author Joseph Manno AKA OblativeShielding */
package com.oblativeshielding.beancounter.gui;

import com.oblativeshielding.beancounter.BeanCounterMain;

/**
 * Custom JavaFX node to handle display of History information.
 */
public class HistoryPanel extends SubPanel {

  // the main program that this panel should interact with
  BeanCounterMain main;

  /**
   * Sets the BeanCounterMain that this panel should refer to.
   * 
   * This must be called at least once before other methods.
   */
  public void setMain(BeanCounterMain main) {
    this.main = main;
  }

  /**
   * Re-renders the GUI visuals (presumably based on updated information).
   */
  public void redraw() {



  }
  
}
