// CMSC 335, Summer 2025
// Project 1, May 27, 2025
// Purpose: Concrete Rectangle class inherits from the TwoDimensionalShape class,
// calculates the area of a rectangle based on the length and width entered by the
// user, and outputs the result to the screen.

public class Rectangle extends TwoDimensionalShape {
    private double length;
    private double width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    public double getArea() {
        return length * width;
    }

    @Override
    public String getName() {
        return "Rectangle";
    }
}
