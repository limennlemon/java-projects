/***************************
 CMSC 335, Project 2, 6/8/25 (Summer 2025) 
 Purpose: MainDemo.java (main class) provides a dropdown menu for the user
 to select a shape (circle, square, triangle, rectangle, sphere, cube,
 cone, cylinder, or torus) to view on the screen.
 ***************************/

package com.example.project2_shapesapplicationdemo;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Node;

public class MainDemo extends Application {

    private Group shapeGroup3D = new Group();

    @Override
    public void start(Stage primaryStage) {
        // Select shape dropdown
        ComboBox<String> shapeComboBox = new ComboBox<>();
        shapeComboBox.getItems().addAll(
                "Circle", "Square", "Triangle", "Rectangle",
                "Sphere", "Cube", "Cone", "Cylinder", "Torus"
        );
        shapeComboBox.setValue("Circle");

        // Select size dropdown
        ComboBox<String> sizeComboBox = new ComboBox<>();
        sizeComboBox.getItems().addAll("Small", "Medium", "Large");
        sizeComboBox.setValue("Medium");

        // Placeholders for dropdown menus
        Label shapeLabel = new Label("Select Shape:");
        Label sizeLabel = new Label("Select Size:");

        HBox controls = new HBox(20, shapeLabel, shapeComboBox, sizeLabel, sizeComboBox);
        controls.setAlignment(Pos.CENTER);
        controls.setStyle("-fx-padding: 25px 0 0 0;");

        BorderPane root = new BorderPane();
        root.setTop(controls);

        SubScene subScene3D = new SubScene(shapeGroup3D, 600, 400, true, SceneAntialiasing.BALANCED);
        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setTranslateZ(-600);
        camera.setNearClip(0.1);
        camera.setFarClip(1000.0);
        subScene3D.setCamera(camera);
        root.setCenter(subScene3D);

        Scene scene = new Scene(root, 700, 500, true);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Shape Selection Application");
        primaryStage.show();

        updateShape(shapeComboBox.getValue(), sizeComboBox.getValue());

        shapeComboBox.valueProperty().addListener((obs, oldVal, newVal) -> {
            updateShape(newVal, sizeComboBox.getValue());
        });

        sizeComboBox.valueProperty().addListener((obs, oldVal, newVal) -> {
            updateShape(shapeComboBox.getValue(), newVal);
        });
    }

    private void updateShape(String shapeType, String size) {
        shapeGroup3D.getChildren().clear();

        Node shape = Selections.createShape(shapeType, size);
        if (shape != null) {
            shapeGroup3D.getChildren().add(shape);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
