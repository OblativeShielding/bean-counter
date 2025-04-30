/**@author Joseph Manno AKA OblativeShielding */
package com.oblativeshielding.beancounter.category;

import javafx.scene.paint.Color;

/**
 * Class for managing funds reserved for payment of debts.
 */
public class Debt extends Category {

  /**
   * Constructor for Debt - calls super with same params.
   */
  public Debt(String name, Color color, String refId) {
    super(name, color, refId);
  }
  
}
