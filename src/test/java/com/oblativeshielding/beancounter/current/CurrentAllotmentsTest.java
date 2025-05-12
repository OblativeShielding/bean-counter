/**@author Joseph Manno AKA OblativeShielding */
package com.oblativeshielding.beancounter.current;

import com.oblativeshielding.beancounter.category.Debt;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;

import com.oblativeshielding.beancounter.exception.InsufficientFundsException;

import javafx.scene.paint.Color;

/**
 * Unit tests for the CurrentAllotments class.
 */
public class CurrentAllotmentsTest {
  
  // test instances
  private CurrentAllotments ca;
  private Debt testCategory1;
  private Debt testCategory2;

  // test color - should be black
  private final Color testColor = new Color(0.0, 0.0, 0.0, 0.0);

  /**
   * Prepare a fresh instance of CurrentAllotments for each test.
   */
  @BeforeEach
  public void testPrep() {

    ca = new CurrentAllotments();
    testCategory1 = new Debt("Test 1", testColor, "T1");
    testCategory2 = new Debt("Test 2", testColor, "T2");

  }

  /**
   * Test that the sum of allotments is correct.
   */
  @Test
  public void sumTest() {

    // add $2 to T1
    ca.addFunds(200, "T1");
    // add $30 to T2
    ca.addFunds(3000, "T2");
    // assert total is $32
    assertTrue(ca.getTotal() == 3200);

  }

  /**
   * Tests that removing more funds than exist in an allotment throws an InsufficientFundsError.
   */
  @Test
  public void insufficientFundsErrorTest() {
    ca.addFunds(3500, "T1");
    InsufficientFundsException thrown = assertThrows(
      InsufficientFundsException.class, () -> ca.removeFunds(4000, "T1"));
    System.out.println(thrown.getMessage());
  }

}
