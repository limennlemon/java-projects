/***************************
Purpose: Graduate class, a subclass of the Student class, has an additional instance 
variable that reflects whether the student is pursuing a Master's degree or doctorate;  
toString() returns the student's name, GPA, and degree. 
***************************/

public class Graduate extends Student {
	
	// declare variables
    private String degree;

    // constructor
    public Graduate(String name, int creditHours, int qualityPoints, String degree) {
        super(name, creditHours, qualityPoints);
        this.degree = degree;
    }
    
    // limit honor society eligibility to student's pursuing Master's degree
    @Override
    public boolean eligibleForHonorSociety() {
        return super.eligibleForHonorSociety() && degree.equals("Master\'s");
    }
   
    // display to screen student's name, GPA, and degree
    @Override
    public String toString() {
        return super.toString() + "  Degree: " + degree;
    }
}
