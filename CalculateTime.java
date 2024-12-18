import java.util.Scanner;

public class CalculateTime {
	 
	public static void main(String[] args) {

		Scanner input = new Scanner(System.in); 

		// Prompt user to enter milliseconds
		System.out.print("Enter milliseconds: ");
		long millis = input.nextLong();

		// Display hh:mm:ss to screen
		System.out.println("hh:mm:ss: " + convertMillis(millis));
	}

	// convertMillis()converts to hh:mm:ss */
	public static String convertMillis(long millis) {
		// convert to seconds
		millis = millis / 1000;

		// convert to hh:mm:ss
		String convertMillis = "";
		for (int i = 0; i < 2; i++) {
			convertMillis = ":" + millis % 60 + convertMillis;
			millis = millis / 60;
		}
		return millis + convertMillis;
	}
}
