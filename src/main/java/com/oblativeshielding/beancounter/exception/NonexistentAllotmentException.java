/**@author Joseph Manno AKA OblativeShielding */
package com.oblativeshielding.beancounter.exception;

/**
 * Custom exception thrown when a referenced allotment does not exist.
 */
public class NonexistentAllotmentException extends RuntimeException {
  
  /**
   * Constructor for NonexistentAllotmentException.
   */
  public NonexistentAllotmentException(String message) {
    super(message);
  }

}
