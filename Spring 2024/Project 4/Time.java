/***************************
Purpose: The Time class contains variables that represent hours, minutes, and meridian values (AM, PM) and returns the values in the form HH:MM AM
***************************/

import java.util.regex.*;

public class Time implements Comparable<Time> {
    private final int hours;
    private final int minutes;
    private final String meridian;

    public Time(int hours, int minutes, String meridian) {
        this.hours = hours;
        this.minutes = minutes;
        this.meridian = meridian;
    }

    // Parse timeString and extract hours, minutes, and meridian
    public Time(String timeString) throws InvalidTime {
        Pattern pattern = Pattern.compile("^(\\d{1,2}):(\\d{2})\\s+(AM|PM)$");
        Matcher matcher = pattern.matcher(timeString);

        if (matcher.matches()) {
            int parsedHours = Integer.parseInt(matcher.group(1));
            int parsedMinutes = Integer.parseInt(matcher.group(2));
            String parsedMeridian = matcher.group(3);

            if ((parsedHours >= 1 && parsedHours <= 12) && (parsedMinutes >= 0 && parsedMinutes <= 59)) {
                hours = parsedHours;
                minutes = parsedMinutes;
                meridian = parsedMeridian;
            } else {
                throw new InvalidTime("Invalid time format or values.");
            }
        } else {
            throw new InvalidTime("Invalid time format.");
        }
    }  

    @Override
    // Compare time intervals
    public int compareTo(Time other) {
        if (meridian.equals("AM") && other.meridian.equals("PM")) {
            return -1;
        } else if (meridian.equals("PM") && other.meridian.equals("AM")) {
            return 1;
        } else {
            int firstInterval = (meridian.equals("AM") ? hours : (hours == 12 ? 12 : hours + 12));
            int secondInterval = (other.meridian.equals("AM") ? other.hours : (other.hours == 12 ? 12 : other.hours + 12));
            if (firstInterval == secondInterval) {
                if (minutes == other.minutes) {
                    return 0;
                } else {
                    return Integer.compare(minutes, other.minutes);
                }
            } else {
                return Integer.compare(firstInterval, secondInterval);
            }
        }
    }

    @Override
    // Return string representation of time in HH:MM AM/PM format
    public String toString() {
        return String.format("%02d:%02d %s", hours, minutes, meridian);
    }
}