/*  
 * CMSC 330 Advanced Programming Languages
 * Project 1, February 2, 2025 
 * SolidPolygon.java is an abstract base class for all solid polygon classes.
 */

import java.awt.*;

// Class that defines all solid polygons

class SolidPolygon extends Polygon_ {

    // Constructor that calls super constructor
	
    public SolidPolygon(Color color, int vertexCount) {
        super(color, vertexCount);
    }
    
    // Draws solid polygon

    @Override
    public void drawPolygon(Graphics graphics, Polygon polygon) {
        graphics.fillPolygon(polygon);
    }
}