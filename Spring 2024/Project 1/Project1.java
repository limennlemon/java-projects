/***************************
Purpose: Project1 (main class) prompts user for the name, height, and age 
of each player, computes the average age, and identifies the tallest
player whose height is equal to or below the average age.
***************************/

import java.util.ArrayList;
import java.util.Scanner;

class Project1 {
    public static void main (String[] args) {
    	
    	// create Player ArrayList
    	ArrayList<Player> players = new ArrayList<>(); 
        Scanner sc = new Scanner(System.in);
        int totalAge = 0; // initialize totalAge
        
        // prompt user for player information
        String promptUser; 
        String uppercasePromptUser; 
        do {
            System.out.print("Please enter name: ");
            String name = sc.nextLine();
            System.out.print("Please enter height in feet/inches format: \nfeet: ");		 
            int feet = sc.nextInt();
            System.out.print("inches: ");
            int inches = sc.nextInt();
            System.out.print("Please enter age: ");
            int age = sc.nextInt();           
            sc.nextLine();
            
            // create Height and Player objects
            Height height = new Height(feet, inches);
            Player player = new Player(name, height, age);
            
            // add player to ArrayList
            players.add(player); 
            
            // calculate running totalAge 
            totalAge = totalAge + age; 
            
            // prompt user to continue/exit
            System.out.print("Enter another record? (Y/N): "); 
            promptUser = sc.nextLine();
            uppercasePromptUser = promptUser.toUpperCase();
            
         } while (uppercasePromptUser.equals("Y")); 
        
        // determine ArrayList size
        int averageAge = totalAge / players.size();  
        // print total number of players and average age to screen
        System.out.println("Total number of players: " + players.size());
        System.out.println("Average age: " + averageAge);
        
        // initialize
        int maxInches = 0;
        Player tallest = null;
        //traverse array to determine tallest with average age equal to or below average age
        for (int i = 0; i < players.size(); i++) {
    	    Player player = players.get(i);
		    if (player.getHeight().toInches() > maxInches && player.getAge() <= averageAge) {
               tallest = player;
               maxInches = player.getHeight().toInches();
            }
        }
        // display to screen
        System.out.println("Tallest player equal to or below average age: " + tallest.toString());
    }
}
