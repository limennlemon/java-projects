// CMSC 335, Summer 2025
// Project 1, May 27, 2025
// Purpose: Concrete Circle class inherits from the TwoDimensionalShape class,
// calculates the area of a circle based on the radius entered by the
// user, and outputs the result to the screen.

public class Circle extends TwoDimensionalShape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double getArea() {
       return Math.PI * radius * radius;
    }

    @Override
    public String getName() {
        return "Circle";
    }
}
