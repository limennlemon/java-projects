import java.util.Scanner;

public class SSN {

	public static void main(String[] args) {
            Scanner input = new Scanner(System.in);

            // Prompt the user to enter a Social Security number
            System.out.print("Enter your Social Security Number: ");
            String ssn = input.nextLine();

            // Check whether the input is valid
            boolean checkSSN = 
                (ssn.length() == 11) && 
                (Character.isDigit(ssn.charAt(0))) &&
                (Character.isDigit(ssn.charAt(1))) &&
                (Character.isDigit(ssn.charAt(2))) &&
                (ssn.charAt(3) == '-') &&
                (Character.isDigit(ssn.charAt(4))) &&
                (Character.isDigit(ssn.charAt(5))) &&
                (ssn.charAt(6) == '-') &&
                (Character.isDigit(ssn.charAt(7))) &&
                (Character.isDigit(ssn.charAt(8))) &&
                (Character.isDigit(ssn.charAt(9))) && 
                (Character.isDigit(ssn.charAt(10)));

            // Display result
            System.out.println(ssn + " is " + ((checkSSN) ? "a valid " : "an invalid ")
            + "Social Security Number.");
	}
}
