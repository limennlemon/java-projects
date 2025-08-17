// CMSC 335, Summer 2025
// Project 1, May 27, 2025
// Purpose: Abstract TwoDimensionalShape class inherits from the Shape class and
// calculates the area of a 2-dimensional a shape based on the width and height
// entered by the user.

public abstract class TwoDimensionalShape extends Shape {
    public TwoDimensionalShape() {
        super(2);
    }

    public abstract double getArea();
}

