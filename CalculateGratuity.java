/*
Purpose: Program to calculate the gratuity amount and final cost based on user input
Course: CMIS 115 Introductory Programming
Date: October 29, 2023
*/

import java.util.Scanner; // Scanner is in the java.util package

public class CalculateGratuity {

    public static void main(String[] args) {
        
        // Create a Scanner object
        Scanner input = new Scanner(System.in);
             
        // Prompt user to enter subtotal and gratuity rate
        System.out.print("Enter the subtotal: "  );
        double subTotal = input.nextDouble();
        System.out.print("Enter the gratuity rate (as a                        percentage): "  );
        double gratuityRate = input.nextDouble();
        
        // Computes the gratuity amount and total cost
        double gratuity = (gratuityRate * subTotal) / 100;
        double totalCost = subTotal + gratuity;
        
        // Display total cost
        System.out.println("The final gratuity and cost based on your input:");
        System.out.printf("Gratuity: $%.2f\n", gratuity); 
        System.out.printf("Total cost: $%.2f\n", totalCost);
    }
}
