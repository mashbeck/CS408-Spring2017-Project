package boilerhungry.backend;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by Matthew on 2/4/2017.
 */
public class Menu {

    private LocalDate date;
    private Map<String, Meal> meals;

    public Menu(LocalDate date) {
        this.date = date;
        this.meals = new HashMap<>();
    }

    public LocalDate getDate() {
        return date;
    }

    public Map<String, Meal> getMeals() {
        return meals;
    }

    public Collection<String> getMealNames() {
        return meals.keySet();
    }

    public Optional<Meal> getMeal(String mealName) {
        return Optional.ofNullable(meals.get(mealName));
    }

}