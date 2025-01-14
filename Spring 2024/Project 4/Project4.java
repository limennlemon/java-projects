/***************************
Purpose: Project4 (main class) displays a GUI interface for comparing two time intervals.
***************************/

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;


public class Project4 extends Application {
  private TextField tfTimeIntervalStart1 = new TextField();
  private TextField tfTimeIntervalEnd1 = new TextField();
  private TextField tfTimeIntervalStart2 = new TextField();
  private TextField tfTimeIntervalEnd2 = new TextField();
  private Button btCompareIntervals = new Button("Compare Intervals");
  private TextField tfTimeToCheck = new TextField();
  private Button btCheckTime = new Button("Check Time");
  private TextField tfCheckTime = new TextField();

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    // Create GUI
    GridPane gridPane = new GridPane();
    gridPane.setHgap(5);
    gridPane.setVgap(5);  
    // Create labels and fields
    Label label1 = new Label("Start Time");
    Label label2 = new Label("End Time"); 
    GridPane.setHalignment(label1, HPos.CENTER);
    GridPane.setValignment(label1, VPos.CENTER);
    GridPane.setHalignment(label2, HPos.CENTER);
    GridPane.setValignment(label2, VPos.CENTER);    
    gridPane.add(label1, 1, 0, 1, 1);
    gridPane.add(label2, 2, 0, 1, 1);     
    gridPane.add(new Label("Time Interval 1 "), 0, 1, 1, 1);
    gridPane.add(tfTimeIntervalStart1, 1, 1, 1, 1);
    gridPane.add(tfTimeIntervalEnd1, 2, 1, 1, 1);
    gridPane.add(new Label("Time Interval 2 "), 0, 2, 1, 1);
    gridPane.add(tfTimeIntervalStart2, 1, 2, 1, 1);
    gridPane.add(tfTimeIntervalEnd2, 2, 2, 1, 1);
    gridPane.add(btCompareIntervals, 0, 3, 3, 1);
    gridPane.add(new Label("Time to Check "), 0, 4, 1, 1);
    gridPane.add(tfTimeToCheck, 1, 4, 2, 1);
    gridPane.add(btCheckTime, 0, 5, 3, 1);
    gridPane.add(tfCheckTime, 0, 6, 3, 1);
    // Set properties for UI
    gridPane.setAlignment(Pos.CENTER);
    tfTimeIntervalStart1.setAlignment(Pos.CENTER);
    tfTimeIntervalEnd1.setAlignment(Pos.CENTER);
    tfTimeIntervalStart2.setAlignment(Pos.CENTER);
    tfTimeIntervalEnd2.setAlignment(Pos.CENTER);
    GridPane.setHalignment(btCompareIntervals, HPos.CENTER);
    btCompareIntervals.setPrefWidth(500);
    tfTimeToCheck.setAlignment(Pos.CENTER);
    GridPane.setHalignment(btCheckTime, HPos.CENTER);
    btCheckTime.setPrefWidth(500);
    tfCheckTime.setEditable(false);

    // Create a scene and place it in the stage
    Scene scene = new Scene(gridPane, 500, 300);
    primaryStage.setTitle("Trip Interval Checker"); // Set title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    // Add event handlers for button 
    btCompareIntervals.setOnAction(event -> {
        try {
            // Parse input interval start and end times
            Time interval1Start = new Time(tfTimeIntervalStart1.getText());
            Time interval1End = new Time(tfTimeIntervalEnd1.getText());
            Time interval2Start = new Time(tfTimeIntervalStart2.getText());
            Time interval2End = new Time(tfTimeIntervalEnd2.getText());

            // Ensure that interval start times are less than or equal to end times
            if (interval1Start.compareTo(interval1End) <= 0 && interval2Start.compareTo(interval2End)    <= 0) {
                // Create interval objects
                Interval<Time> interval1 = new Interval<>(interval1Start, interval1End);
                Interval<Time> interval2 = new Interval<>(interval2Start, interval2End);

                if (interval1.subinterval(interval2)) {
                    // Interval 1 is subinterval of interval 2
                	tfCheckTime.setText("Interval 1 is a sub-interval of interval 2");
	                } else if (interval2.subinterval(interval1)) {
	                    // Interval 2 is subinterval of interval 1
	                    tfCheckTime.setText("Interval 2 is a sub-interval of interval 1");
	                } else if (interval1.overlaps(interval2)) {
	                    // The intervals overlap
	                    tfCheckTime.setText("The intervals overlap");
	                } else {
	                    // The intervals are disjoint
	                    tfCheckTime.setText("The intervals are disjoint");
	                }
            } else {
                tfCheckTime.setText("Invalid interval input: Start time must begin before or be equal to end time");
            }
        } catch (InvalidTime ex) {
            tfCheckTime.setText("Invalid time input: " + ex.getMessage());
        }
    });

    btCheckTime.setOnAction(event -> {
        try {
            // Parse time to check
            Time timeToCheck = new Time(tfTimeToCheck.getText());

            // Parse input interval start and end times
            Time interval1Start = new Time(tfTimeIntervalStart1.getText());
            Time interval1End = new Time(tfTimeIntervalEnd1.getText());
            Time interval2Start = new Time(tfTimeIntervalStart2.getText());
            Time interval2End = new Time(tfTimeIntervalEnd2.getText());

            // Check if time is within intervals
            boolean isInsideInterval1 = interval1Start.compareTo(timeToCheck) <= 0                    				&& interval1End.compareTo(timeToCheck) >= 0;

            boolean isInsideInterval2 = interval2Start.compareTo(timeToCheck) <= 0                    				&& interval2End.compareTo(timeToCheck) >= 0;

            if (isInsideInterval1 && isInsideInterval2) {
            		tfCheckTime.setText("Both intervals contain the time " + timeToCheck);
	            } else if (isInsideInterval1) {
	                tfCheckTime.setText("Only Interval 1 contains the time " + timeToCheck);
	            } else if (isInsideInterval2) {
	                tfCheckTime.setText("Only Interval 2 contains the time " + timeToCheck);
	            } else {
	                tfCheckTime.setText("Neither interval contains the time " + timeToCheck);
	            }
        } catch (InvalidTime ex) {
        	tfCheckTime.setText("Invalid time input: " + ex.getMessage());
        }
    });
}

  // Main method
  public static void main(String[] args) {
    launch(args);
  }
}