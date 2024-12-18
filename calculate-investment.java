import java.util.Scanner; // Scanner is in the java.util package

public class calculate-investment {

    public static void main(String[] args) {
        
        // Create a Scanner object
        Scanner input = new Scanner(System.in);
             
        // Prompt user to enter the investment amount, annual interest rate, and number of years
        System.out.print("Enter the amount you are investing: "  );
        double investmentAmount = input.nextDouble();
        System.out.print("Enter the annual interest rate: "  );
        double annualInterestRate = input.nextDouble();
        System.out.print("Enter the number of years: "  );
        int numberOfYears = input.nextInt();
        
        // Computes monthly interest rate
        double monthlyInterestRate = annualInterestRate / 1200; 
        
        // Computes future investment value based 
        double futureInvestmentValue = investmentAmount * Math.pow((1.0 + monthlyInterestRate), (numberOfYears * 12));
        
        
        // Display future value of the investment
         System.out.printf("The future value of your investment is: $%.2f\n", futureInvestmentValue);
    }
}
