// CMSC 335, Summer 2025
// Project 1, May 27, 2025
// Purpose: Concrete Cone class inherits from the ThreeDimensionalShape class,
// calculates the volume of a cube based on the radius and height entered by the
// user, and outputs the result to the screen.

public class Cone extends ThreeDimensionalShape {
    private double radius2;
    private double height2;

    public Cone(double radius2, double height2) {
        this.radius2 = radius2;
        this.height2 = height2;
    }

    @Override
    public double getVolume() {
        return ((1.0 / 3) * Math.PI * Math.pow(radius2, 2) * height2);
    }

    @Override
    public String getName() {
        return "Cone";
    }
}
