package boilerhungry;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Hours {

    private LocalTime startTime;
    private LocalTime endTime;
    private boolean isOpen;
    public static final DateTimeFormatter TIME_FORMAT_IN = DateTimeFormatter.ISO_LOCAL_TIME;
    public static final DateTimeFormatter TIME_FORMAT_OUT = DateTimeFormatter.ofPattern("h:mm a");


    public Hours(String startTime, String endTime) throws DateTimeParseException  {
        this.startTime = LocalTime.parse(startTime, TIME_FORMAT_IN);
        this.endTime = LocalTime.parse(endTime, TIME_FORMAT_IN);
        this.isOpen = true;
    }

    public Hours(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.isOpen = true;
    }

    public Hours() {
        this.isOpen = false;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    @Override
    public String toString() {
        if (isOpen) {
            //return String.format("%s - %s", startTime.format(TIME_FORMAT_OUT), endTime.format(TIME_FORMAT_OUT));
            //return String.format(" - %s", endTime.format(TIME_FORMAT_OUT));
            return " - 11:59pm";
        } else {
            return "closed";
        }
    }

}
