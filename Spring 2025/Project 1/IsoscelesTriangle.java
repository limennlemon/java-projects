/*  
 * CMSC 330 Advanced Programming Languages
 * Project 1, February 2, 2025 
 * IsoscelesTriangle.java class defines the isosceles triangle.
 */

import java.awt.*;

//Class that defines a isosceles triangle

class IsoscelesTriangle extends SolidPolygon {
	
	// Constructor that initializes the vertices of the isosceles triangle
 
    public IsoscelesTriangle(Color color, Point upperLeft, int height, int width) {
        super(color, 3);
        int[] xPoints = {upperLeft.x - width / 2, upperLeft.x + width / 2, upperLeft.x};
        int[] yPoints = {upperLeft.y + height, upperLeft.y + height, upperLeft.y};
        createPolygon(xPoints, yPoints); 
    }
}