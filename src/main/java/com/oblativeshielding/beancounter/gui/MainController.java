/**@author Joseph Manno AKA OblativeShielding */
package com.oblativeshielding.beancounter.gui;

import com.oblativeshielding.beancounter.BeanCounterMain;
import com.oblativeshielding.beancounter.category.Category;
import com.oblativeshielding.beancounter.exception.InsufficientFundsException;
import com.oblativeshielding.beancounter.exception.NonexistentAllotmentException;
import java.io.IOException;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * Controller for main GUI window.
 */
public class MainController {

  // instance of main to interact with
  private final BeanCounterMain main = new BeanCounterMain();
  
  // smaller panels
  @FXML private BalancePanel balancePanel;
  @FXML private BudgetPanel budgetPanel;
  @FXML private HistoryPanel historyPanel;

  // dialogs
  private LogExpenseDialog logExpenseDialog;
  private AddFundsDialog addFundsDialog;
  private ReallocateDialog reallocateDialog;
  private NewCategoryDialog newCategoryDialog;

  /** Called when FXML loaded to finish setup */
  @FXML
  protected void initialize() {

    // tell sub panels about main
    balancePanel.setMain(main);
    budgetPanel.setMain(main);
    historyPanel.setMain(main);

    // redraw the panels
    balancePanel.redraw();
    budgetPanel.redraw();
    historyPanel.redraw();

    // pepare dialogs
    logExpenseDialog = new LogExpenseDialog();
    addFundsDialog = new AddFundsDialog();
    reallocateDialog = new ReallocateDialog();
    newCategoryDialog = new NewCategoryDialog();

  }

  @FXML @SuppressWarnings("unused")
  private void logExpense() {

    logExpenseDialog.update();
    logExpenseDialog.show();

  }

  @FXML @SuppressWarnings("unused")
  private void addFunds() {

    addFundsDialog.update();
    addFundsDialog.show();

  }

  @FXML @SuppressWarnings("unused")
  private void reallocate() {

    reallocateDialog.update();
    reallocateDialog.show();

  }

  @FXML @SuppressWarnings("unused")
  private void newCategory() {

    newCategoryDialog.clear();
    newCategoryDialog.show();

  }

  @FXML @SuppressWarnings("unused")
  private void printCurrent() {

    try {
      main.printStatus("status.txt");
      Alert a = new Alert(AlertType.INFORMATION, "Current status printed to \"status.txt\".");
      a.showAndWait();
    } catch (IOException e) {
      Alert a = new Alert(AlertType.ERROR, "Unable to print status.");
    }

  }

  @FXML @SuppressWarnings("unused")
  private void saveBudget() {

    notYetImplemented();
    
  }

  @FXML @SuppressWarnings("unused")
  private void loadBudget() {

    notYetImplemented();
    
  }

  @FXML @SuppressWarnings("unused")
  private void newBudget() {

    notYetImplemented();
    
  }

  @FXML @SuppressWarnings("unused")
  private void clearHistory() {

    notYetImplemented();
    
  }

  /** Temporary alert shortcut function. */
  private void notYetImplemented() {
    Alert a = new Alert(Alert.AlertType.INFORMATION, "This is not yet implemented. Sorry!");
    a.showAndWait();
  }



  /**
   * New Expense button dialog
   */
  private class LogExpenseDialog extends Stage {

    private final ChoiceBox<Category> categoryChoice;
    private final TextField dollarAmount;

    private LogExpenseDialog() {

      this.initModality(Modality.APPLICATION_MODAL);

      categoryChoice = new ChoiceBox<>();
      dollarAmount = new TextField();

      // set up category choice box
      categoryChoice.setConverter(new StringConverter<Category>(){

            @Override
            public String toString(Category c) {
              if (c == null) {
                return "Unallocated";
              } else {
                return c.getName();
              }
            }
            
            @Override
            public Category fromString(String s) {
              return null;
            }

          });

      // input validator mostly from ChatGPT
      Pattern validDecimalText = Pattern.compile("\\d*(\\.\\d{0,2})?");
      UnaryOperator<TextFormatter.Change> filter = change -> {
            String newText = change.getControlNewText();
            if (validDecimalText.matcher(newText).matches()) {
                return change;
            }
            return null;
          };
      dollarAmount.setTextFormatter(new TextFormatter<String>(filter));

      Button cancelButton = new Button("Cancel");
      cancelButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> this.hide());

      Button submitButton = new Button("Submit");
      submitButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> this.submit());

      GridPane grid = new GridPane();
      grid.add(new Label("Category:"), 0, 0);
      grid.add(categoryChoice, 1, 0);
      grid.add(new Label("Amount:"), 0, 1);
      grid.add(dollarAmount, 1, 1);
      grid.add(cancelButton, 0, 2);
      grid.add(submitButton, 1, 2);
      this.setScene(new Scene(grid));

    }

    // update the category choices and reset the amount input
    private void update() {

      categoryChoice.getItems().clear();
      categoryChoice.getItems().addAll(main.getCategories());
      categoryChoice.getItems().add(null);

      dollarAmount.setText("");

    }

    private void submit() {

      if(dollarAmount.getText().trim().length() == 0) {
        Alert a = new Alert(Alert.AlertType.WARNING, "Please enter an amount to spend.");
        a.showAndWait();
      } else {
        
        int amount = (int) (Double.parseDouble(dollarAmount.getText()) * 100);
        Category selected = categoryChoice.getValue();
        try{
          main.logExpense(amount, (selected != null ? selected.getRefId() : null));
          balancePanel.redraw();
          this.hide();
        } catch(InsufficientFundsException | NonexistentAllotmentException e) {
          Alert a = new Alert(AlertType.ERROR, "This category does not have sufficient funds.");
          a.showAndWait();
        }

      }

    }

  }

  /**
   * Add Funds button dialog
   */
  private class AddFundsDialog extends Stage {

    private final ChoiceBox<Category> categoryChoice;
    private final TextField dollarAmount;

    private AddFundsDialog() {

      this.initModality(Modality.APPLICATION_MODAL);

      categoryChoice = new ChoiceBox<>();
      dollarAmount = new TextField();

      // set up category choice box
      categoryChoice.setConverter(new StringConverter<Category>(){

            @Override
            public String toString(Category c) {
              if (c == null) {
                return "Unallocated";
              } else {
                return c.getName();
              }
            }
            
            @Override
            public Category fromString(String s) {
              return null;
            }

          });

      // input validator mostly from ChatGPT
      Pattern validDecimalText = Pattern.compile("\\d*(\\.\\d{0,2})?");
      UnaryOperator<TextFormatter.Change> filter = change -> {
            String newText = change.getControlNewText();
            if (validDecimalText.matcher(newText).matches()) {
                return change;
            }
            return null;
          };
      dollarAmount.setTextFormatter(new TextFormatter<String>(filter));

      Button cancelButton = new Button("Cancel");
      cancelButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> this.hide());

      Button submitButton = new Button("Submit");
      submitButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> this.submit());

      GridPane grid = new GridPane();
      grid.add(new Label("Category:"), 0, 0);
      grid.add(categoryChoice, 1, 0);
      grid.add(new Label("Amount:"), 0, 1);
      grid.add(dollarAmount, 1, 1);
      grid.add(cancelButton, 0, 2);
      grid.add(submitButton, 1, 2);
      this.setScene(new Scene(grid));

    }

    // update the category choices and reset the amount input
    private void update() {

      categoryChoice.getItems().clear();
      categoryChoice.getItems().addAll(main.getCategories());
      categoryChoice.getItems().add(null);

      dollarAmount.setText("");

    }

    private void submit() {

      if(dollarAmount.getText().trim().length() == 0) {
        Alert a = new Alert(Alert.AlertType.WARNING, "Please enter an amount to add.");
        a.showAndWait();
      } else {
      
        int amount = (int) (Double.parseDouble(dollarAmount.getText()) * 100);
        Category selected = categoryChoice.getValue();
        main.addFunds(amount, (selected != null ? selected.getRefId() : null));
        balancePanel.redraw();
        this.hide();

      }

    }

  }

  /**
   * Reallocate button dialog
   */
  private class ReallocateDialog extends Stage {
    
    private final ChoiceBox<Category> sourceCategory;
    private final TextField dollarAmount;
    private final ChoiceBox<Category> destinationCategory;

    private ReallocateDialog() {

      this.initModality(Modality.APPLICATION_MODAL);

      sourceCategory = new ChoiceBox<>();
      dollarAmount = new TextField();
      destinationCategory = new ChoiceBox<>();

      // set up category choice box
      sourceCategory.setConverter(new StringConverter<Category>(){

            @Override
            public String toString(Category c) {
              if (c == null) {
                return "Unallocated";
              } else {
                return c.getName();
              }
            }
            
            @Override
            public Category fromString(String s) {
              return null;
            }

          });

      // input validator mostly from ChatGPT
      Pattern validDecimalText = Pattern.compile("\\d*(\\.\\d{0,2})?");
      UnaryOperator<TextFormatter.Change> filter = change -> {
            String newText = change.getControlNewText();
            if (validDecimalText.matcher(newText).matches()) {
                return change;
            }
            return null;
          };
      dollarAmount.setTextFormatter(new TextFormatter<String>(filter));

      // set up category choice box
      destinationCategory.setConverter(new StringConverter<Category>(){

            @Override
            public String toString(Category c) {
              if (c == null) {
                return "Unallocated";
              } else {
                return c.getName();
              }
            }
            
            @Override
            public Category fromString(String s) {
              return null;
            }

          });

      Button cancelButton = new Button("Cancel");
      cancelButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> this.hide());

      Button submitButton = new Button("Submit");
      submitButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> this.submit());

      GridPane grid = new GridPane();
      grid.add(new Label("From:"), 0, 0);
      grid.add(sourceCategory, 1, 0);
      grid.add(new Label("Amount:"), 0, 1);
      grid.add(dollarAmount, 1, 1);
      grid.add(new Label("To:"), 0, 2);
      grid.add(destinationCategory, 1, 2);
      grid.add(cancelButton, 0, 3);
      grid.add(submitButton, 1, 3);
      this.setScene(new Scene(grid));

    }

    // update the category choices and reset the amount input
    private void update() {

      sourceCategory.getItems().clear();
      sourceCategory.getItems().addAll(main.getCategories());
      sourceCategory.getItems().add(null);

      dollarAmount.setText("");

      destinationCategory.getItems().clear();
      destinationCategory.getItems().addAll(main.getCategories());
      destinationCategory.getItems().add(null);

    }

    private void submit() {
      
      if(dollarAmount.getText().trim().length() == 0) {
        Alert a = new Alert(Alert.AlertType.WARNING, "Please enter an amount to transfer.");
        a.showAndWait();
      } else {

        Category source = sourceCategory.getValue();
        int amount = (int) (Double.parseDouble(dollarAmount.getText()) * 100);
        Category dest = destinationCategory.getValue();

        try{
          main.transfer(amount,
              source != null ? source.getRefId() : null,
              dest != null ? dest.getRefId() : null
              );
          balancePanel.redraw();
          this.hide();
        } catch(InsufficientFundsException | NonexistentAllotmentException e) {
          Alert a = new Alert(AlertType.ERROR, "This allotment does not have sufficient funds.");
          a.showAndWait();
        }

      }

    }

  }

  /**
   * New Category button dialog
   */
  private class NewCategoryDialog extends Stage {

    private final TextField newCategoryName;

    // set up dialog
    private NewCategoryDialog() {

      this.initModality(Modality.APPLICATION_MODAL);

      newCategoryName = new TextField();

      Button cancelButton = new Button("Cancel");
      cancelButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> this.hide());

      Button submitButton = new Button("Submit");
      submitButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> this.submit());

      GridPane grid = new GridPane();
      grid.add(new Label("Category Name:"), 0, 0);
      grid.add(newCategoryName, 1, 0);
      grid.add(cancelButton, 0, 1);
      grid.add(submitButton, 1, 1);
      Scene scene = new Scene(grid);
      this.setScene(scene);

    }

    // submit function
    private void submit() {
      // make sure input isn't whitespace
      if(newCategoryName.getText().trim().length() == 0) {
        Alert a = new Alert(Alert.AlertType.WARNING, "Please enter a name for the category.");
        a.showAndWait();
      } else {
        //TODO add color functionality
        main.addNewCategory(newCategoryName.getText(), new Color(0.0,0.0,0.0, 0.0));
        balancePanel.redraw();
        this.hide();
      }
    }

    // clear function
    private void clear() {
      newCategoryName.clear();
    }

  }

}
