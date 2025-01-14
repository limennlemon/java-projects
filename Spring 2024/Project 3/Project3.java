/***************************
Purpose: Project3 (main class) defines the GUI interface for a Trip Cost Calculator. The Trip Cost Calculator program calculates the total cost of a trip based on user input about the trip such as the distance, number of days, and associated costs of the hotel and food.
***************************/

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Project3_draft2 extends Application {
  private TextField tfDistance = new TextField();
  private TextField tfGasCost = new TextField();
  private TextField tfGasMileage = new TextField();
  private TextField tfNumberOfDays = new TextField();
  private TextField tfHotelCost = new TextField();
  private TextField tfFoodCost = new TextField();
  private TextField tfAttractions = new TextField();
  private TextField tfTotalTripCost = new TextField();
  private ComboBox<String> distanceComboBox = new ComboBox<>();
  private ComboBox<String> gasolineCostComboBox = new ComboBox<>();
  private ComboBox<String> gasMileageComboBox = new ComboBox<>();
  private Button btCalculate = new Button("Calculate");

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    // Create UI
    GridPane gridPane = new GridPane();
    gridPane.setHgap(5);
    gridPane.setVgap(5);
    gridPane.add(new Label("Distance:"), 0, 0);
    gridPane.add(tfDistance, 1, 0);
    distanceComboBox.getItems().addAll("miles", "kilometers");
    distanceComboBox.setValue("miles");
    gridPane.add(distanceComboBox, 2, 0);
    distanceComboBox.setPrefWidth(150);
    gridPane.add(new Label("Gasoline Cost:"), 0, 1);
    gridPane.add(tfGasCost, 1, 1);
    gasolineCostComboBox.getItems().addAll("dollars/gal", "dollars/liter");
    gasolineCostComboBox.setValue("dollars/gal");
    gridPane.add(gasolineCostComboBox, 2, 1);
    gasolineCostComboBox.setPrefWidth(150);
    gasMileageComboBox.getItems().addAll("miles/gal", "kilometers/liter");
    gasMileageComboBox.setValue("miles/gal");
    gridPane.add(gasMileageComboBox, 2, 2);
    gasMileageComboBox.setPrefWidth(150);
    gridPane.add(new Label("Gas Mileage:"), 0, 2);
    gridPane.add(tfGasMileage, 1, 2);
    gridPane.add(new Label("Number of Days:"), 0, 3);
    gridPane.add(tfNumberOfDays, 1, 3);
    gridPane.add(new Label("Hotel Cost:"), 0, 4);
    gridPane.add(tfHotelCost, 1, 4);
    gridPane.add(new Label("Food Cost:"), 0, 5);
    gridPane.add(tfFoodCost, 1, 5);
    gridPane.add(new Label("Attractions:"), 0, 6);
    gridPane.add(tfAttractions, 1, 6);
    gridPane.add(btCalculate, 1, 7);
    gridPane.add(new Label("Total Trip Cost:"), 0, 8);
    gridPane.add(tfTotalTripCost, 1, 8);

    // Set properties for UI
    gridPane.setAlignment(Pos.CENTER);
    tfDistance.setAlignment(Pos.BOTTOM_RIGHT);
    tfGasCost.setAlignment(Pos.BOTTOM_RIGHT);
    tfGasMileage.setAlignment(Pos.BOTTOM_RIGHT);
    tfNumberOfDays.setAlignment(Pos.BOTTOM_RIGHT);
    tfHotelCost.setAlignment(Pos.BOTTOM_RIGHT);
    tfFoodCost.setAlignment(Pos.BOTTOM_RIGHT);
    tfAttractions.setAlignment(Pos.BOTTOM_RIGHT);
    GridPane.setHalignment(btCalculate, HPos.RIGHT);
    btCalculate.setPrefWidth(150);
    tfTotalTripCost.setEditable(false);
    
    // Process events
    btCalculate.setOnAction(e -> calculateTripCost());

    // Create a scene and place it in the stage
    Scene scene = new Scene(gridPane, 600, 450);
    primaryStage.setTitle("Trip Cost Estimator"); // Set title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
  }
  
  private void calculateTripCost() {
	  
	// try-catch to catch when user enters something other than a number in the fields
	try {
	    // Get values from text fields
	    int distance = Integer.parseInt(tfDistance.getText());
	    double gasCost = Double.parseDouble(tfGasCost.getText());
	    double gasMileage = Double.parseDouble(tfGasMileage.getText());
	    int days = Integer.parseInt(tfNumberOfDays.getText());
	    double hotel = Double.parseDouble(tfHotelCost.getText());
	    double food = Double.parseDouble(tfFoodCost.getText());
	    double attractions = Double.parseDouble(tfAttractions.getText());
	    
        // Convert units as needed so that final calculations are based on same 
        // units of miles, dollars/gal, and miles/gal respectively 
	    if (distanceComboBox.getValue().equals("kilometers")) {
            distance *= 0.621371; // Convert kilometers to miles
        }
	    if (gasolineCostComboBox.getValue().equals("dollars/liter")) {
            gasCost *= 3.78541; // Convert dollars per liter to dollars per gallon
        }
	    if (gasMileageComboBox.getValue().equals("kilometers/liter")) {
            gasMileage *= 0.264172; // Convert kilometers per liter to miles per gallon
        }  

	    // Create a trip object
	    TripCost trip01 = new TripCost(distance, gasCost, gasMileage, days, hotel, food, attractions);

	    // Display total trip cost
	    tfTotalTripCost.setText(String.format("$%.2f",
	      trip01.calculateTripCost()));
	  }
   catch (NumberFormatException ex) {
      btCalculate.setText("Invalid input.");
  }
  }
  
  // main method
  public static void main(String[] args) {
    launch(args);
  }
}
