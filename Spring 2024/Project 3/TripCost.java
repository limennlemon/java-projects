/***************************
Purpose: The TripCost class has seven instance variables (distance, gasCost, gasMileage, days, hotel, food, and attractions) which are used to calculate the total cost of the trip using the calculateTripCost() method.
***************************/

public class TripCost {
	  private int distance;
	  private double gasCost;
	  private double gasMileage;
	  private int days;
	  private double hotel;
	  private double food; 
	  private double attractions;

	  // No-arg constructor 
	  public TripCost() {
	    }

	  // Construct a trip with specified information  
	  public TripCost(int distance, double gasCost, double gasMileage, int days, double hotel, double food, double attractions) {
	    this.distance = distance;
	    this.gasCost = gasCost;
	    this.gasMileage = gasMileage;
	    this.hotel = hotel;
	    this.food = food;
	    this.days = days;
	    this.attractions = attractions;
	  }

	  // Calculate total trip cost 
	  public double calculateTripCost() { 
	    double totalGasCost = 0;
	    double totalTripCost = 0; 
	    totalGasCost = (distance / gasMileage) * gasCost; 
	    totalTripCost = totalGasCost + 
	    		((hotel + food) * days) + attractions;
	    return totalTripCost;    
	  }
	}