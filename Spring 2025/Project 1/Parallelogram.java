/*  
 * CMSC 330 Advanced Programming Languages
 * Project 1, February 2, 2025 
 * Parallelogram.java class defines the parallelogram.
 */

import java.awt.*;

//Class that defines a solid parallelogram object

class Parallelogram extends SolidPolygon {
	
	 // Constructor that initializes the vertices of the parallelogram
  
	 public Parallelogram(Color color, Point upperLeft, Point lowerRight, int offset) {
        super(color, 4);
        int[] xPoints = {upperLeft.x, lowerRight.x, lowerRight.x - offset, upperLeft.x - offset};
        int[] yPoints = {upperLeft.y, upperLeft.y, lowerRight.y, lowerRight.y};
        createPolygon(xPoints, yPoints);
    }
}