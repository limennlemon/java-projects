// CMSC 335, Summer 2025
// Project 1, May 27, 2025
// Purpose: Concrete Sphere class inherits from the ThreeDimensionalShape class,
// calculates the volume of a sphere based on the radius entered by the
// user, and outputs the result to the screen.

public class Sphere extends ThreeDimensionalShape {
    private double radius;

    public Sphere(double radius) {
       this.radius = radius;
    }

    @Override
    public double getVolume() {
        return (4.0 / 3.0) * Math.PI * Math.pow(radius, 3);
    }

    @Override
    public String getName() {
        return "Sphere";
    }
}
