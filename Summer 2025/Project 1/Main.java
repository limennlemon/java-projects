// CMSC 335, Summer 2025
// Project 1, May 27, 2025
// Purpose: The Main class is the central program that manages the input and output of
// the program through a command-line interface.

import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("\n********* Welcome to the Java OO Shapes Program **********");
            System.out.println("Select from the menu below:");
            System.out.println("1. Construct a Circle");
            System.out.println("2. Construct a Rectangle");
            System.out.println("3. Construct a Square");
            System.out.println("4. Construct a Triangle");
            System.out.println("5. Construct a Sphere");
            System.out.println("6. Construct a Cube");
            System.out.println("7. Construct a Cone");
            System.out.println("8. Construct a Cylinder");
            System.out.println("9. Construct a Torus");
            System.out.println("10. Exit the program");

            int choice = getValidInt(scanner, "Your choice: ", 1, 10);

            switch (choice) {
                case 1:
                    double radius = getValidDouble(scanner, "Enter radius: ");
                    Circle circle = new Circle(radius);
                    System.out.printf("The area of the Circle is %.2f%n", circle.getArea());
                    break;
                case 2:
                    double length = getValidDouble(scanner, "Enter length: ");
                    double width = getValidDouble(scanner, "Enter width: ");
                    Rectangle rect = new Rectangle(length, width);
                    System.out.printf("The area of the Rectangle is %.2f%n", rect.getArea());
                    break;
                case 3:
                    double side = getValidDouble(scanner, "Enter length of side: ");
                    Square square = new Square(side);
                    System.out.printf("The area of the Square is %.2f%n", square.getArea());
                    break;
                case 4:
                    double base = getValidDouble(scanner, "Enter base of triangle: ");
                    double height = getValidDouble(scanner, "Enter height of triangle: ");
                    Triangle triangle = new Triangle(base, height);
                    System.out.printf("The area of the Triangle is %.2f%n", triangle.getArea());
                    break;
                case 5:
                    double sphereRadius = getValidDouble(scanner, "Enter radius of the Sphere: ");
                    Sphere sphere = new Sphere(sphereRadius);
                    System.out.printf("The volume of the Sphere is %.2f%n", sphere.getVolume());
                    break;
                case 6:
                    double cubeRadius = getValidDouble(scanner, "Enter radius of the Cube: ");
                    Cube cube = new Cube(cubeRadius);
                    System.out.printf("The volume of the Cube is %.2f%n", cube.getVolume());
                    break;
                case 7:
                    double radius2 = getValidDouble(scanner, "Enter radius of the Cone: ");
                    double height2 = getValidDouble(scanner, "Enter height of the Cone: ");
                    Cone cone = new Cone(radius2, height2);
                    System.out.printf("The volume of the Cube is %.2f%n", cone.getVolume());
                    break;
                case 8:
                    double radius3 = getValidDouble(scanner, "Enter radius of the Cylinder: ");
                    double height3 = getValidDouble(scanner, "Enter height of the Cylinder: ");
                    Cylinder cylinder = new Cylinder(radius3, height3);
                    System.out.printf("The volume of the Cylinder is %.2f%n", cylinder.getVolume());
                    break;
                case 9:
                    double majorRadius = getValidDouble(scanner, "Enter the distance from center of hole to center of tube (Major Radius): ");
                    double minorRadius = getValidDouble(scanner, "Enter the radius of the tube (Minor Radius): ");
                    Torus torus = new Torus(majorRadius, minorRadius);
                    System.out.printf("The volume of the Torus is %.2f%n", torus.getVolume());
                    break;
                case 10:
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd 'at' h:mm a");
                    System.out.println("Thanks for using the program. Today is " + LocalDateTime.now().format(formatter) + ".");
                    running = false;
                    break;
                default:
                    System.out.println("Shape not implemented yet.");
                    break;
            }

            while (true) {
                System.out.print("Would you like to continue? (Y or N): ");
                String cont = scanner.next();

                if (cont.equalsIgnoreCase("Y")) {
                    break;  // Valid input, exit the loop
                } else if (cont.equalsIgnoreCase("N")) {
                    running = false;
                    System.out.println("Thanks for using the program!");
                    break;  // Valid input, exit the loop and end program
                } else {
                    System.out.println("I do not understand");  // Invalid input message
                                   }
            }
        }
    }

    private static int getValidInt(Scanner scanner, String prompt, int min, int max) {
        int input;
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                if (input >= min && input <= max) {
                    return input;
                }
            }
            scanner.nextLine(); // clear buffer
            System.out.println("Invalid input. Try again.");
        }
    }

    private static double getValidDouble(Scanner scanner, String prompt) {
        double input;
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextDouble()) {
                input = scanner.nextDouble();
                return input;
            }
            scanner.nextLine(); // clear buffer
            System.out.println("Invalid input. Try again.");
        }
    }
}
