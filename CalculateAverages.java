import java.util.Scanner;

public class CalculateAverages {
    
    public static void main(String[] args) {
        
        float sum = 0;
        int count = 0;
        int countPositive = 0;
        int countNegative = 0;
    
        Scanner input = new Scanner(System.in);
        
        // prompt for integer
        System.out.print("Enter an integer (the input ends if is it 0): ");
        int data = input.nextInt();
            // if 0 entered print to scren
            if (data == 0) {
                System.out.println("No numbers entered except 0");
                System.exit(0);
            }
            else {

                // keep reading data until the input is 0
                while (data != 0) {
                    
                        // count number of positive and negative numbers
                        if (data > 0) {
                            countPositive += 1;
                        }
                        else {
                            countNegative += 1; 
                        }
                    sum += data;
                    count += 1;
                    // read the next data
                    data = input.nextInt();
                    }

                // calculate average
                float average = sum / count;

                // print results to screen
  System.out.println("The number of positive numbers is " +  countPositive);
  System.out.println("The number of negative numbers is " + countNegative);
                System.out.println("The total is " + sum);
                System.out.println("The average is " + average);
                System.out.println(count);
                    }

    }
}
