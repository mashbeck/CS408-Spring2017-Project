package boilerhungry.backend;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eric on 2/17/17.
 */
public class Meal {

    private String name;
    private List<Food> foods;
    private Hours hours;

    public Meal(String name, Hours hours, List<Food> foods) {
        this.name = name;
        this.hours = hours;
        this.foods = foods;
    }

    public Meal(String name, Hours hours) {
        this.name = name;
        this.hours = hours;
        this.foods = new ArrayList<>();
    }

    public List<Food> getFoods() {
        return foods;
    }

    public String getName() {
        return name;
    }

    public Hours getHours() {
        return hours;
    }

}
