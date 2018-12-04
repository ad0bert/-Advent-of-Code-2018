package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SuppressWarnings("rawtypes")
public class Day4DateTimeString implements Comparable {

    public LocalDateTime time;
    public String entry;

    // [1518-05-28 00:59] wakes up
    public Day4DateTimeString(String input) {
        String arr[] = input.split("]");
        this.entry = arr[1].trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.time = LocalDateTime.parse(arr[0].substring(1, arr[0].length()), formatter);
    }

    @Override
    public int compareTo(Object o) {
        return this.time.compareTo(((Day4DateTimeString) o).time);
    }

    @Override
    public String toString() {
        return this.time.toString() + " " + this.entry;
    }
}
