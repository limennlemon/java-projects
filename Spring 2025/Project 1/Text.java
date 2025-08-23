/*  
 * CMSC 330 Advanced Programming Languages
 * Project 1, February 2, 2025 
 * Text.java class defines text.
 */

import java.awt.*;

class Text extends Image {
    private Point location;
    private String text;

    public Text(Color color, Point location, String text) {
    	super(color);
        this.location = location;
        this.text = text;
    }

    public void draw(Graphics graphics) {
    	colorDrawing(graphics);
    	graphics.drawString(text, location.x, location.y);
    }
}