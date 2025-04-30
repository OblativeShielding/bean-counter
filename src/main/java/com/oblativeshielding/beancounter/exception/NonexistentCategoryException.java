/**@author Joseph Manno AKA OblativeShielding */
package com.oblativeshielding.beancounter.exception;

/**
 * Custom exception thrown when a referenced category does not exist.
 */
public class NonexistentCategoryException extends RuntimeException {
  
  /**
   * Constructor for NonexistentCategoryException.
   */
  public NonexistentCategoryException(String message) {
    super(message);
  }

}
