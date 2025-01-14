/**
 * Delimiter Checker Program (Main) Class: Prompts 
 * user for source file and calls the DelimiterChecker   
 * class to read through each character of the Java 
 * source file to determine if the delimiters match.
*/

import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

public class DelimiterCheckerProgram  {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
			String fileName;
			DelimiterChecker delimiterChecker;

			do {
			    System.out.print("Enter filename and path: ");
			    fileName = scanner.nextLine();
			    try {
			        delimiterChecker = new DelimiterChecker(fileName);
			        break; 
			    } catch (IOException e) {
			        System.out.println("File not found. Please try again.");
			    }
			} while (true);

			Stack<Character> delimiterStack = new Stack<>();

			try {
			    char currentChar;
			    while ((currentChar = delimiterChecker.getNextChar()) != 0) {
			        if (isLeftDelimiter(currentChar)) {
			            delimiterStack.push(currentChar);
			        } else if (isRightDelimiter(currentChar)) {
			            if (delimiterStack.isEmpty()) {
			                System.out.println("Mismatched delimiter " + currentChar + " on " + delimiterChecker.getCurrentPosition());
			                return; 
			            }

			            char leftDelimiter = delimiterStack.pop();
			            if (!isMatching(leftDelimiter, currentChar)) {
			                System.out.println("Mismatched delimiter " + currentChar + " on " + delimiterChecker.getCurrentPosition());
			                return; 
			            }
			        }
			    }

			    if (!delimiterStack.isEmpty()) {
			        System.out.println("Unmatched left delimiters in the stack.");
			    } else {
			        System.out.println("All delimiters are matched.");
			    }

			} catch (IOException e) {
			    System.out.println("Error reading file: " + e.getMessage());
			}
		}
    }

    private static boolean isLeftDelimiter(char ch) {
        return ch == '(' || ch == '[' || ch == '{';
    }

    private static boolean isRightDelimiter(char ch) {
        return ch == ')' || ch == ']' || ch == '}';
    }

    private static boolean isMatching(char left, char right) {
        return (left == '(' && right == ')') ||
               (left == '[' && right == ']') ||
               (left == '{' && right == '}');
    }
}
