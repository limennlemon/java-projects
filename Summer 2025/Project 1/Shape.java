// CMSC 335, Summer 2025
// Project 1, May 27, 2025
// Purpose: Abstract Shape class representing a generic Shape, determines the number
// of dimensions for a given shape, and uses an abstract method to get the Shape's name.

public abstract class Shape {
    protected int numberOfDimensions;

    public Shape(int numberOfDimensions) {
         this.numberOfDimensions = numberOfDimensions;
    }

    public int getNumberOfDimensions() {
        return numberOfDimensions;
    }

    public abstract String getName();
}
