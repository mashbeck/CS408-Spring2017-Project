package boilerhungry.backend;

import java.time.LocalDate;
import java.util.*;

/**
 * Created by Matthew on 2/4/2017.
 */
public class Menu {

    private LocalDate date;
    private Map<String, List<Food>> meals;

    public Menu(LocalDate date) {
        this.date = date;
        this.meals = new HashMap<>();
    }

    public LocalDate getDate() {
        return date;
    }

    public Map<String, List<Food>> getMeals() {
        return meals;
    }

    public Collection<String> getMealNames() {
        return meals.keySet();
    }

    public List<Food> getMeal(String mealName) {
        return meals.getOrDefault(mealName, new ArrayList<>());
    }

}