// CMSC 335, Summer 2025
// Project 1, May 27, 2025
// Purpose: Concrete Torus class inherits from the ThreeDimensionalShape class,
// calculates the volume of a torus based on the major and minor radii entered by the
// user, and outputs the result to the screen.

public class Torus extends ThreeDimensionalShape {
    private double majorRadius;
    private double minorRadius;

    public Torus(double majorRadius, double minorRadius) {
        this.majorRadius = majorRadius;
        this.minorRadius = minorRadius;
    }

    @Override
    public double getVolume() {
         return 2 * Math.PI * Math.PI * majorRadius * Math.pow(minorRadius, 2);
    }

    @Override
    public String getName() {
        return "Torus";
    }
}
