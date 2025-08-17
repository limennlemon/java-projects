// CMSC 335, Summer 2025
// Project 1, May 27, 2025
// Purpose: Concrete Cone class inherits from the ThreeDimensionalShape class,
// calculates the volume of a cube based on the radius and height entered by the
// user, and outputs the result to the screen.

public class Cylinder extends ThreeDimensionalShape {
    private double radius3;
    private double height3;

    public Cylinder(double radius3, double height3) {
        this.radius3 = radius3;
        this.height3 = height3;
    }

    @Override
    public double getVolume() {
        return (Math.PI * Math.pow(radius3, 2) * height3);
    }

    @Override
    public String getName() {
        return "Cylinder";
    }
}
