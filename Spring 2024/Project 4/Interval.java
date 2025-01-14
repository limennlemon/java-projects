/***************************
Purpose: The Interval class is a generic class that defines objects that have a start and end of the generic type parameter.
***************************/

public class Interval<T extends Comparable<T>> {
    private final T start;
    private final T end;

    public Interval(T start, T end) {
        this.start = start;
        this.end = end;
    }

    // Return whether object is inside interval
    public boolean within(T value) {
        return value.compareTo(start) >= 0 && value.compareTo(end) <= 0;
    }
    
    // Return whether interval parameter is a subinterval or completely within the interval  
    public boolean subinterval(Interval<T> interval) {
        return interval.start.compareTo(start) >= 0 && interval.end.compareTo(end) <= 0;
    }

    // Return whether interval parameters overlap 
    public boolean overlaps(Interval<T> interval) {
        return interval.start.compareTo(end) <= 0 && interval.end.compareTo(start) >= 0;
    }
}