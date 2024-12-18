import java.util.Scanner;

public class PointsInRectangle {
    
  public static void main(String[] args) {
	  
        Scanner input = new Scanner(System.in);
                
        double x, y;
        
        System.out.print("Enter a point with two coordinates: ");
        x = input.nextDouble();
        y = input.nextDouble();

        if(x < 0) {
          x = x * (-1);
        }

        if(y < 0) {
          y = y * (-1);
        }

        if((x < 10.0/2) && (y < 5.0/2)) {
System.out.print("Point (" + x + ", " + y + ") is in the rectangle.");
         } 
        else {
System.out.print("Point (" + x + ", " + y + ") is not in the rectangle.");
        }
  } 
}
