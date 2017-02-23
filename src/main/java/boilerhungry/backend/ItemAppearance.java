package boilerhungry.backend;

import boilerhungry.backend.DiningCourt;
import boilerhungry.backend.Meal;

import java.time.format.DateTimeFormatter;

/**
 * Created by Lena Adel on 2/22/2017.
 */
public class ItemAppearance {
    private String searchFood;
    private String diningCourt;
    private String station;
    private String meal;
    private String date;

    public ItemAppearance(String searchFood) {
        this.searchFood = searchFood;
    }
    public String getSearchFood() {
        return searchFood;
    }
    public String getStation() {

        return station;
    }
    public String getDiningCourt() {
        return diningCourt;
    }
    public String getMeal() {
        return meal;
    }
    public String getDate() {
        return date;
    }

    public void setSearchFood(String searchFood) {
        this.searchFood = searchFood;
    }
    public void setStation(String station) {
        this.station = station;
    }
    public void setDiningCourt(String diningCourt) {
        this.diningCourt = diningCourt;
    }
    public void setMeal(String meal) {
        this.meal = meal;
    }
    public void setDate(String date) {
        this.date = date;
    }

}
