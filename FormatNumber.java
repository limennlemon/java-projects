import java.util.Scanner;

public class FormatNumber {
    
    public static void main(String[] args) {

    Scanner input = new Scanner(System.in);
    // prompt user to enter milliseconds
    System.out.print("Enter an integer: ");
    int number = input.nextInt(); 
    System.out.print("Enter the width: ");
    int width = input.nextInt(); 
    // Call method to display in correct format
    System.out.println("The formatted number is: " + format(number, width));   
    }
    // method to convert integer and width to correct format
    public static String format(int number, int width) {
        String enterNum = number + ""; // Convert number to a string
        if (enterNum.length() < width) {
                for (int i = width - enterNum.length(); i > 0; i--) {
                        enterNum = 0 + enterNum;
                }
        }
        return enterNum;
}
}
