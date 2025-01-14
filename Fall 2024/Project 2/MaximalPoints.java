/***************************
Purpose: Project2 (MaximalPoints class) extends JavaFX's Pane to draw points and lines, manages event handling for adding and removing points, and dynamically updates the maximal points in real-time. 
***************************/

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class MaximalPoints extends Pane {
	   private ObservableList<Point> points;
	   private ObservableList<Line> lines;
	   public MaximalPoints(ObservableList<Point> points) {
	       this.points = points;
	       this.lines = FXCollections.observableArrayList();
	       this.setOnMouseClicked(this::handleMouseClick);
	       // Add a listener to call recalculateMaximalSet() after height is set
	       this.heightProperty().addListener((obs, oldVal, newVal) -> 
	       recalculateMaximalSet());
	   }
	   private void handleMouseClick(MouseEvent event) {
	       double x = event.getX();
	       double y = event.getY();
	       
	        if (event.getButton() == MouseButton.PRIMARY) { // Adds point left click
	            Point newPoint = new Point(x, y);
	            points.add(newPoint);
	            drawPoint(newPoint);
	        } else if (event.getButton() == MouseButton.SECONDARY) { // Removes point right click
	            Point closestPoint = findClosestPoint(x, y);
	            if (closestPoint != null) {
	                points.remove(closestPoint);
	                getChildren().removeIf(node -> node instanceof Circle && 
	                    ((Circle) node).getCenterX() == closestPoint.getX() && 
	                    ((Circle) node).getCenterY() == closestPoint.getY());
	            }
	        }
	        recalculateMaximalSet();
	   }
	   
	    private Point findClosestPoint(double x, double y) {
	        Point closestPoint = null;
	      double minDistance = Double.MAX_VALUE;

	       for (Point point : points) {
	            double distance = Math.hypot(point.getX() - x, point.getY() - y);
	         if (distance < minDistance) {
	               minDistance = distance;
	                closestPoint = point;
	            }
	      }
	       return closestPoint;
	    }
	    
	    void drawPoint(Point point) {
	        Circle circle = new Circle(point.getX(), point.getY(), 3);
	        circle.setStroke(Color.rgb(0, 0, 0)); 
	        getChildren().add(circle);
	    }
	   
	    private void recalculateMaximalSet() {
			   // Clear existing lines
		        getChildren().removeAll(lines);
		        lines.clear();
		        
		        // Sort points based on x-coordinate
		        List<Point> sortedPoints = new ArrayList<>(points);
		        // Collections.sort(sortedPoints);
		   Collections.sort(sortedPoints, Comparator.comparing(Point::getX).reversed());
		       
		        // Find the maximal points for the staircase effect
		        List<Point> maximalPoints = new ArrayList<>();
		        double currentMinY = Double.POSITIVE_INFINITY;

		        for (Point point : sortedPoints) {
		            if (point.getY() < currentMinY) {
		                maximalPoints.add(point);
		                currentMinY = point.getY();
		            }
		        }
		        /* 
		        // Print the maximal points to the console for debugging
		        System.out.println("Maximal points in final display order:");
		        for (Point p : maximalPoints) {
		            System.out.println("Point(x=" + p.getX() + ", y=" + p.getY() + ")");
		        } 
		        */
		        
		        // Draw lines between maximal points to create the staircase effect
		        for (int i = 0; i < maximalPoints.size() - 1; i++) {
		            Point p1 = maximalPoints.get(i);
		            Point p2 = maximalPoints.get(i + 1);
		        
			    // Draw horizontal line
			    Line horizontalLine = new Line(p1.getX(), p1.getY(), p1.getX(), p2.getY());
			    horizontalLine.setStroke(Color.rgb(0, 0, 0));  
			    lines.add(horizontalLine);
			    getChildren().add(horizontalLine);
			        
			    // Draw vertical line to the next point
			    Line verticalLine = new Line(p1.getX(), p2.getY(), p2.getX(), p2.getY());
			    verticalLine.setStroke(Color.rgb(0, 0, 0));  
			    lines.add(verticalLine);
			    getChildren().add(verticalLine);
			    }
		            
		        // Extend the line from the first maximal point to the left edge of the screen
		        if (!maximalPoints.isEmpty()) {
		            Point firstPoint = maximalPoints.get(maximalPoints.size() - 1);
		            Line leftExtension = new Line(0, firstPoint.getY(), firstPoint.getX(), firstPoint.getY());
		            leftExtension.setStroke(Color.BLACK);
		            lines.add(leftExtension);
		            getChildren().add(leftExtension);

		            // Extend the line from the last maximal point vertically down to the bottom edge of the screen
		            Point lastPoint = maximalPoints.get(0);
		          //  double screenHeight = getHeight(); // Height of the pane
		         //   double screenWidth = getWidth(); // Width of the pane
		            Line verticalExtension = new Line(lastPoint.getX(), lastPoint.getY(), lastPoint.getX(), Math.max(lastPoint.getY(), getHeight()));
		            verticalExtension.setStroke(Color.BLACK);
		            lines.add(verticalExtension);
		            getChildren().add(verticalExtension);
		        }       
		        }

		    }
		       

