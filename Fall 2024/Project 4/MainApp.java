/***************************
Purpose: Project 4 (MainApp class) creates the GUI including all the buttons and fields and their associated event handlers. 
***************************/

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class MainApp extends Application {
	private TextArea outputArea;

	@Override
	public void start(Stage primaryStage) {
		GraphPane graphPane = new GraphPane();

		Button addEdgeButton = new Button("Add Edge");
		TextField vertex1Field = new TextField();
		vertex1Field.setPrefWidth(50);
		TextField vertex2Field = new TextField();
		vertex2Field.setPrefWidth(50);

		outputArea = new TextArea();
		outputArea.setEditable(false);
		outputArea.setPrefHeight(100);
		outputArea.setWrapText(true);

		addEdgeButton.setOnAction(e -> {
			try {
				graphPane.addEdge(vertex1Field.getText(), vertex2Field.getText());
			} catch (IllegalArgumentException ex) {
				showAlert("Error", ex.getMessage());
			}
		});

		HBox topControls = new HBox(10, addEdgeButton, new Label("Vertex 1: "), vertex1Field, new Label("Vertex 2: "),
				vertex2Field);
		topControls.setAlignment(Pos.CENTER);
		topControls.setPadding(new Insets(10));

		Button isConnectedButton = new Button("Is Connected?");
		Button hasCyclesButton = new Button("Has Cycles?");
		Button dfsButton = new Button("Depth First Search");
		Button bfsButton = new Button("Breadth First Search");

		TextArea outputArea = new TextArea();
		outputArea.setEditable(false);
		outputArea.setPrefHeight(100);
		outputArea.setWrapText(true);

		isConnectedButton.setOnAction(e -> {
			boolean connected = graphPane.getGraph().isConnected();
			outputArea.appendText(
					(connected ? "The graph is connected." : "The graph isn't connected.") + "\n");
		});

		hasCyclesButton.setOnAction(e -> {
			boolean cycles = graphPane.getGraph().hasCycles();
			outputArea.appendText(
					(cycles ? "The graph has cycles." : "The graph doesn't have cycles.") + "\n");
		});

		dfsButton.setOnAction(e -> {
			outputArea.appendText("DFS: " + graphPane.getGraph().depthFirstSearch().toString() + "\n");
		});

		bfsButton.setOnAction(e -> {
			outputArea.appendText("BFS: " + graphPane.getGraph().breadthFirstSearch().toString() + "\n");
		});

		HBox bottomButtons = new HBox(10, isConnectedButton, hasCyclesButton, dfsButton, bfsButton);
		bottomButtons.setAlignment(Pos.CENTER);
		bottomButtons.setPadding(new Insets(10));

		VBox bottomSection = new VBox(10, bottomButtons, outputArea);
		bottomSection.setPadding(new Insets(10));

		BorderPane root = new BorderPane();
		root.setTop(topControls);
		root.setCenter(graphPane);
		root.setBottom(bottomSection);

		Scene scene = new Scene(root, 800, 800);
		graphPane.setOnMouseClicked(e -> graphPane.addVertex(e.getX(), e.getY()));

		primaryStage.setScene(scene);
		primaryStage.setTitle("Project 4");
		primaryStage.show();
	}

	private void showAlert(String title, String message) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setContentText(message);
		alert.showAndWait();
	}

	public static void main(String[] args) {
		launch(args);
	}
}