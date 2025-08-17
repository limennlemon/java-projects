// CMSC 335, Summer 2025
// Project 1, May 27, 2025
// Purpose: Concrete Cube class inherits from the ThreeDimensionalShape class,
// calculates the volume of a cube based on the radius entered by the
// user, and outputs the result to the screen.

public class Cube extends ThreeDimensionalShape {
    private double radius;

    public Cube(double radius) {
        this.radius = radius;
    }

    @Override
    public double getVolume() {
        return Math.pow(radius, 3);
    }

    @Override
    public String getName() {
        return "Cube";
    }
}
