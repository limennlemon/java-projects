// CMSC 335, Summer 2025
// Project 1, May 27, 2025
// Purpose: Concrete Square class inherits from the TwoDimensionalShape class,
// calculates the area of a square based on the length entered by the
// user, and outputs the result to the screen.

public class Square extends TwoDimensionalShape {
    private double side;

    public Square(double side) {
        this.side = side;
     }

    @Override
    public double getArea() {
        return side * 2;
    }

    @Override
    public String getName() {
        return "Square";
    }
}