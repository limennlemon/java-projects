public class RectangleMeasurements {
    
    public static void main(String[] args) {
        Rectangle myRectangle01 = new Rectangle(4, 40);
        System.out.println("The area of a rectangle with width " +
                myRectangle01.width + " and height " +
                myRectangle01.height + " is " +
                myRectangle01.getArea());
        System.out.println("The perimeter of a rectangle is " +
                myRectangle01.getPerimeter());
        Rectangle myRectangle02 = new Rectangle(3.5, 35.9);
        System.out.println("The area of a rectangle with width " +
                myRectangle02.width + " and height " +
                myRectangle02.height + " is " +
                myRectangle02.getArea());
        System.out.println("The perimeter of a rectangle is " +
                myRectangle02.getPerimeter());
    }
}

class Rectangle {
    double height = 1.0; 
    double width = 1.0;

    //no-arg constructor
    Rectangle() {}

    //construct rectangle
    Rectangle(double newHeight, double newWidth) {
        height = newHeight;
        width = newWidth;
    }

    //return area
    double getArea() {
        return height * width;  
    }

    //return perimeter 
    double getPerimeter() {
        return 2 (height + width);
    }

}
