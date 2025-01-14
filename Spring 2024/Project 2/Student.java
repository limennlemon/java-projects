/***************************
Purpose: The Student class has three instance variables (student's name, credit hours
earned, and quality points); gpa() returns the student's GPA; eligibleForHonorSociety() 
returns student's with a GPA that exceeds the threshold; setGpaThreshold() sets the 
minimum GPA; toString() returns the student's name and GPA.
***************************/

public class Student {
	
	// declare variables
    private String name;
    private int creditHours;
    private int qualityPoints;
    private static double gpaThreshold;

    // constructor
    public Student(String name, int creditHours, int qualityPoints) {
        this.name = name;
        this.creditHours = creditHours;
        this.qualityPoints = qualityPoints;
    }

    // calculate GPA
    public double gpa() {
        return qualityPoints / (double) creditHours;
    }

    // determine GPA above the threshold limit
    public boolean eligibleForHonorSociety() {
        return gpa() > gpaThreshold;
    }

    // return gpaThreshold
    public static double getGpaThreshold() {
        return gpaThreshold;
    }
    
    // return gpaThreshold
    public static void setGpaThreshold(double threshold) {
        gpaThreshold = threshold;
    }
  
    // display to screen student's name and GPA
    @Override
    public String toString() {
        return "Name: " + name + "  GPA: " + gpa();
    }
}