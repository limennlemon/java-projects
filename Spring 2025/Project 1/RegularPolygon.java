/*  
 * CMSC 330 Advanced Programming Languages
 * Project 1, February 2, 2025 
 * RectanglePolygon.java class defines the regular polygon.
 */

import java.awt.*;

//Class that defines a solid regular polygon object

class RegularPolygon extends SolidPolygon  {
	
	// Constructor that initializes the vertices of the regular polygon
    
    public RegularPolygon(Color color, int numSides, Point center, int radius) {
        super(color, numSides);
	    int[] xPoints = new int[numSides];
	    int[] yPoints = new int[numSides];

	    for (int i = 0; i < numSides; i++) {
	        double angle = 2 * Math.PI * i / numSides;
	        xPoints[i] = (int) (center.x + radius * Math.cos(angle));
	        yPoints[i] = (int) (center.y + radius * Math.sin(angle));
	     }
	     createPolygon(xPoints, yPoints);
    }
}