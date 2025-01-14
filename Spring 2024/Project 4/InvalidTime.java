/***************************
Purpose: The InvalidTime class checks to verify that the time entered is valid. 
***************************/

public class InvalidTime extends Exception {
    public InvalidTime(String message) {
        super(message);
    }
}