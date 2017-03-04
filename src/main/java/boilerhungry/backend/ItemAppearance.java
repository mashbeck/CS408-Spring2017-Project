package boilerhungry.backend;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class ItemAppearance {

    private final String foodName;
    private Optional<String> diningCourt;
    private Optional<String> station;
    private Optional<String> meal;
    private Optional<LocalDateTime> dateTime;

    public ItemAppearance(String foodName) {
        this.foodName = foodName;
        this.diningCourt = Optional.empty();
        this.station = Optional.empty();
        this.meal = Optional.empty();
        this.dateTime = Optional.empty();
    }

    public String getFoodName() {
        return foodName;
    }

    public Optional<String> getStation() {
        return station;
    }

    public Optional<String> getDiningCourt() {
        return diningCourt;
    }

    public Optional<String> getMeal() {
        return meal;
    }

    public Optional<LocalDateTime> getDateTime() {
        return dateTime;
    }

    public void setStation(String station) {
        this.station = Optional.ofNullable(station);
    }

    public void setDiningCourt(String diningCourt) {
        this.diningCourt = Optional.ofNullable(diningCourt);
    }

    public void setMeal(String meal) {
        this.meal = Optional.ofNullable(meal);
    }

    public void setDateTime(LocalDateTime date) {
        this.dateTime = Optional.ofNullable(date);
    }

    @Override
    public String toString() {
        String meal = getMeal().orElse("(unknown)");
        String date = getDateTime().map(dt -> dt.format(DateTimeFormatter.ISO_LOCAL_DATE)).orElse("(unknown)");
        String time = getDateTime().map(dt -> dt.format(DateTimeFormatter.ofPattern("h:mm a"))).orElse("(unknown)");
        String station = getStation().orElse("(unknown)");
        String diningCourt = getDiningCourt().orElse("(unknown)");
        return String.format("%s will be served at %s on %s at %s at the station %s during %s", foodName, diningCourt, date, time, station, meal);
    }

}