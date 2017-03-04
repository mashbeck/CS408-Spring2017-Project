package boilerhungry.backend;

import java.time.LocalDateTime;
import java.util.Optional;

public class ItemAppearance {

    private final String searchFood;
    private Optional<String> diningCourt;
    private Optional<String> station;
    private Optional<String> meal;
    private Optional<LocalDateTime> dateTime;

    public ItemAppearance(String searchFood) {
        this.searchFood = searchFood;
        this.diningCourt = Optional.empty();
        this.station = Optional.empty();
        this.meal = Optional.empty();
        this.dateTime = Optional.empty();
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

}