/***************************
Purpose: Project 4 (GraphPane class) is an extension of the JavaFX Pane that visually displays the graph and includes an event handler that responds to mouse clicks and creates new vertices and a method that is called to draw edges.
***************************/

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class GraphPane extends Pane {
	private final Graph graph = new Graph();
	private char nextVertexLabel = 'A';

	public Graph getGraph() {
		return graph;
	}

	public void addVertex(double x, double y) {
		String label = String.valueOf(nextVertexLabel++);
		graph.addVertex(new Vertex(x, y, label));
		Circle circle = new Circle(x, y, 5); 
        Text text = new Text(x - 4, y - 10, label);  
        text.setFill(Color.BLACK); 
		getChildren().addAll(circle, text);
	}

	public void addEdge(String vertex1, String vertex2) {
		Vertex v1 = graph.getVertices().get(vertex1);
		Vertex v2 = graph.getVertices().get(vertex2);

		if (v1 == null || v2 == null) {
			throw new IllegalArgumentException("One or both vertices do not exist.");
		}

		graph.addEdge(vertex1, vertex2);

		Line line = new Line(v1.getX(), v1.getY(), v2.getX(), v2.getY());
		line.setStroke(Color.BLACK);
		getChildren().add(line);
	}
}