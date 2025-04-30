/**@author Joseph Manno AKA OblativeShielding */
package com.oblativeshielding.beancounter;

import java.util.Scanner;
import javafx.scene.paint.Color;

/** Main class for running BeanCounter from the command line. */
public class BeanCounterHeadless {

  // the main instance of BeanCounterMain
  private final BeanCounterMain main;
  // loop condition
  private boolean keepGoing;
  // input scanner
  private final Scanner scanner;

  // command help string constant
  private static final String COMMAND_HELP =
      """
      help - provides a list of available commands
      addc - adds a new category
      addf - adds funds to a selected category
      disp - prints a list of categories, allotments, and funds
      """;

  /**
   * Initiates the program to run via the command prompt. 
   */
  public static void main(String[] args) {
    
    // instatiate main
    BeanCounterHeadless bcHeadless = new BeanCounterHeadless();
    bcHeadless.go();

  }

  /**
   * Constructor for BeanCounterHeadless
   */
  public BeanCounterHeadless() {

    main = new BeanCounterMain();
    keepGoing = true;
    scanner = new Scanner(System.in);

  }

  /** This function actually provides the main input loop for the command line interface. */
  private void go() {

    System.out.println("Welcome to Bean Counter! Type \"help\" for a list of commands.\n");

    // main input loop
    while(keepGoing) {

      // read and process input
      String input = scanner.nextLine();
      switch(input) {
        case "help" -> System.out.println(COMMAND_HELP);
        case "addc" -> addCategory();
        case "addf" -> addFunds();
        case "disp" -> display();
        case "quit" -> keepGoing = false;
        default -> System.out.println("Invalid input. Please try again.");
      }

    }

    System.out.println("Terminating program.\nGoodbye!\n");

  }

  /** add a new category */
  private void addCategory() {
    System.out.print("Enter the name of the new category: ");
    String categoryName = scanner.nextLine();
    if(categoryName.equals("")) {
      System.out.println("Category addition canceled.");
    } else {
      main.addNewCategory(categoryName, new Color(0.0,0.0,0.0,0.0));
    }
  }

  /** add funds to a category */
  private void addFunds() {
    System.out.print("Enter the reference ID of the category you wish to add funds to: ");
    
  }

  /** print a list of categories, allotments, and funds */
  private void display() {
    
  }


}
