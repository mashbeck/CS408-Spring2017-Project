package boilerhungry.backend;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Created by eric on 2/17/17.
 */
public class Hours {

    private LocalTime startTime;
    private LocalTime endTime;
    public static final DateTimeFormatter TIME_FORMAT_IN = DateTimeFormatter.ofPattern("HH:mm:ss");
    public static final DateTimeFormatter TIME_FORMAT_OUT = DateTimeFormatter.ofPattern("hh:mm a");


    public Hours(String startTime, String endTime) throws DateTimeParseException  {
        this.startTime = LocalTime.parse(startTime, TIME_FORMAT_IN);
        this.endTime = LocalTime.parse(endTime, TIME_FORMAT_IN);
    }

    public Hours(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    @Override
    public String toString() {
        return String.format("%s - %s", startTime.format(TIME_FORMAT_OUT), endTime.format(TIME_FORMAT_OUT));
    }

}
