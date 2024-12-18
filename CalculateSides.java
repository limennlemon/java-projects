import java.util.Scanner;

public class CalculateSides {

    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        
        // Prompt user for the number of sides
        System.out.print("Enter the number of sides: ");
        int n = input.nextInt();
         
        // Prompt user for the length of the sides
        System.out.print("Enter the length of the sides: ");
      double s = input.nextDouble();
        
        // Calculate the area
       double area = n * Math.pow(s, 2) / (4 * (Math.tan(Math.PI / n))); 
               
        // Display area on the screen        
        System.out.print("The area of the polygon is: " + area);
    }
    
}
