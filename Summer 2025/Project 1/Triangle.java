// CMSC 335, Summer 2025
// Project 1, May 27, 2025
// Purpose: Concrete Triangle class inherits from the TwoDimensionalShape class,
// calculates the area of a triangle based on the base and height entered by the
// user, and outputs the result to the screen.

public class Triangle extends TwoDimensionalShape {
    private double base;
    private double height;

    public Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }

    @Override
    public double getArea() {
        return (base * height) * 0.5;
    }

    @Override
    public String getName() {
        return "Triangle";
    }
}
