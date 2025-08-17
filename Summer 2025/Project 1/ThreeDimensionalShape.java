// CMSC 335, Summer 2025
// Project 1, May 27, 2025
// Purpose: Abstract ThreeDimensionalShape class inherits from the Shape class and
// calculates the volume of a 3-dimensional shape.

public abstract class ThreeDimensionalShape extends Shape {
    public ThreeDimensionalShape() {
        super(3);
    }

    public abstract double getVolume();
}

