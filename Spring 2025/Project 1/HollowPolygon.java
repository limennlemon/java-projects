/*  
 * CMSC 330 Advanced Programming Languages
 * Project 1, February 2, 2025 
 * HollowPolygon.java class defines all hollow polygons. 
 */

import java.awt.*;

// Class that defines all hollow polygons

class HollowPolygon extends Polygon_ {

    // Constructor that calls super constructor

    public HollowPolygon(Color color, int vertexCount) {
        super(color, vertexCount);
    }

    // Draws hollow polygon

    @Override
    public void drawPolygon(Graphics graphics, Polygon polygon) {
        graphics.drawPolygon(polygon);
    }
}