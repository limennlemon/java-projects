/***************************
Purpose: Project2 (Main class) reads input from a file and displays the results in a JavaFX window. 
***************************/

import javafx.application.Application; 
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MaximalPointsDemo extends Application {
    @Override
    public void start(Stage primaryStage) {
        ObservableList<Point> initialPoints = readPointsFromFile("points1.txt");
        MaximalPoints pane = new MaximalPoints(initialPoints);
        
        // Draw initial points
        for (Point initialPoint : initialPoints) {
            pane.drawPoint(initialPoint);
        }
        
        Scene scene = new Scene(pane, 500, 500);
        primaryStage.setTitle("Maximal Points Visualization");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private ObservableList<Point> readPointsFromFile(String filename) {
    	ObservableList<Point> points = FXCollections.observableArrayList();
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextDouble()) {
                double x = scanner.nextDouble();
                double y = scanner.nextDouble();
                points.add(new Point(x, y));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return points;
    }

    public static void main(String[] args) {
        launch(args);
    }
} 