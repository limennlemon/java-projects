/***************************
Purpose: The Undergraduate class, a subclass of the Student class, has an additional 
instance variable that specifies the student's year (freshman, sophomore, junior, 
or senior); toString() returns the student's name, GPA, and year. 
***************************/

public class Undergraduate extends Student {
	
	// declare variables
    private String year;

    // constructor
    public Undergraduate(String name, int creditHours, int qualityPoints, String year) {
        super(name, creditHours, qualityPoints);
        this.year = year;
    }
    
    // limit honor society eligibility to juniors and seniors
    @Override
    public boolean eligibleForHonorSociety() {
        return super.eligibleForHonorSociety() && (year.equals("Junior") || year.equals("Senior"));
    }
    
    // display to screen student's name, GPA, and year
    @Override
    public String toString() {
        return super.toString() + "  Year: " + year;
    }
}