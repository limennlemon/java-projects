/***************************
Purpose: Height class accepts int values for feet and inches, toInches() returns 
height in total inches, toString() returns the height using single/double 
quotes (e.g., 5'10") and only displays inches under 12
***************************/

public class Height {
    private int feet;
    private int inches;

    // constructor
    public Height(int feet, int inches) {
        this.feet = feet;
        this.inches = inches;
    }

    // getter
    public int getFeet() {
        return feet;
    }

    // getter
    public int getInches() {
        return inches;
    }
    
    // method to calculate total inches
    public int toInches() {
        return (feet * 12) + inches;
    }
    
    @Override
    // displays result in traditional height format (e.g., 5'10")
    public String toString() {
        int displayInches = inches % 12;
        int displayFeet = feet + (inches / 12);
        return displayFeet + "'" + displayInches + "\"";
    }

}

