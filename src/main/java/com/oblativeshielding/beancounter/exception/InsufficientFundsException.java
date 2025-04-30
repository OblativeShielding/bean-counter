/**@author Joseph Manno AKA OblativeShielding */
package com.oblativeshielding.beancounter.exception;

/**
 * Custom exception thrown when trying to remove more funds than are available.
 */
public class InsufficientFundsException extends RuntimeException {
  
  /**
   * Constructor for InsufficientFundsException - super RuntimeException()
   */
  public InsufficientFundsException(String message) {
    super(message);
  }

  /**
   * Constructor for InsufficientFundsException with default message
   */
  public InsufficientFundsException() {
    this("You require more Vespene Gas.");
  }

}
