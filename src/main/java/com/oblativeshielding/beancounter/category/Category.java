/**@author Joseph Manno AKA OblativeShielding */
package com.oblativeshielding.beancounter.category;

import javafx.scene.paint.Color;

/** Abstract parent class for money categories. */
public abstract class Category {
  
  // Display name
  private String name;
  // Display color
  private Color color;

  /** Constructor method for Category. */
  public Category(Color color, String name) {
    this.name = name;
    this.color = color;
  }
  
  /** Sets the name of this category. */
  public void setName(String s) {
    this.name = s;
  }

  /** Gets the name of this category.
   * 
   * @return a String representing the name of this category
   */
  public String getName() {
    return this.name;
  }

  /** Sets the color of this category. */
  public void setColor(Color c) {
    this.color = c;
  }

  /** Gets the color of this category.
   * 
   * @return the Color object assigned to this category
   */
  public Color getColor() {
    return this.color;
  }

}
