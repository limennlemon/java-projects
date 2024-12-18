import java.util.Scanner;

public class LocationArray {
  public static void main(String[] args) {
      
        Scanner in = new Scanner(System.in);

        System.out.print("Enter the number of rows and columns in the array: ");
        int rows = in.nextInt();
        int columns = in.nextInt();

        double[][] userArray = new double[rows][columns];

        System.out.println("Enter the array:");

        for (int i = 0; i < userArray.length; i++) {
            for (int j = 0; j < userArray[i].length; j++) {
                userArray[i][j] = in.nextDouble();
            }
        }
 
        Location myLocation = Location.locateLargest(userArray);
        
        System.out.printf("The location of the largest element is: "
                + "%.2f at (%d, %d)", myLocation.maxValue, myLocation.rows,
                myLocation.columns);
  }
}

    class Location {
        public double maxValue = Integer.MIN_VALUE;
        public int rows;
        public int columns;

        public static Location locateLargest(double[][] a) {
            Location location = new Location();
            for (int i = 0; i < a.length; i++) {
                for (int j = 0; j < a[i].length; j++) {
                    if (a[i][j] > location.maxValue) {
                        location.maxValue = a[i][j];
                        location.rows = i;
                        location.columns = j;
                    }

                }
            }
            return location;
        }

}
