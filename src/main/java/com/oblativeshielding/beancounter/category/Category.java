/**@author Joseph Manno AKA OblativeShielding */
package com.oblativeshielding.beancounter.category;

import javafx.scene.paint.Color;

/**
 * Abstract parent class for money categories.
 */
public abstract class Category implements Comparable<Category> {
  
  // Display name
  private String name;
  // Display color
  private Color color;
  // Reference ID
  private final String refId;

  /** Constructor method for Category. */
  public Category(String name, Color color, String refId) {
  
    // set instance attributes
    this.name = name;
    this.color = color;
    this.refId = refId;

  }
  
  /** Sets the name of this category. */
  public void setName(String s) {
    this.name = s;
  }

  /**
   * Gets the name of this category.
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

  /**
   * Gets the color of this category.
   * 
   * @return the Color object assigned to this category
   */
  public Color getColor() {
    return this.color;
  }

  /**
   * Gets the reference ID of this category.
   * 
   * @return a String denoting the reference ID of this Category object
   */
  public String getRefId() {
    return refId;
  }

  /**
   * Override of compareTo method for Category objects.
   * 
   * Compares based on names first, then reference IDs.
   * 
   * At the time of writing, this should be consistent with equals.
   * Ideally, however, no two extant Categories will be equal because refId should be unique.
   * 
   * @return result of compareTo for names, or reference IDs if names are equal.
   */
  @Override
  public int compareTo(Category other) {
    // check names
    int result = name.compareTo(other.getName());
    if(result != 0) {
      return result;
    } else {
      // if equal, check reference IDs next
      return refId.compareTo(other.getRefId());
    }
  }

  /**
   * Override of equals method to make compareTo consistent with equals.
   * 
   * Ignores the color attribute because JavaFX has no simple way to compare colors.
   * 
   * @return true if name and reference ID are equal, otherwise false.
   */
  @Override
  public boolean equals(Object o) {
    if(o == this) {
      return true;
    } else if(o instanceof Category other) {
      // check equality of attributes
      return name.equals(other.getName()) && refId.equals(other.getRefId());
    } else {
      // not comparing two Category objects
      return false;
    }
  }

  /**
   * Override of hashCode() to keep equals happy.
   * 
   * @return super.hashCode()
   */
  @Override
  public int hashCode() {
    return super.hashCode();
  }

}
