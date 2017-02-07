package boilerhungry.backend;

import java.util.*;

/**
 * Created by Matthew on 2/4/2017.
 */
public class Menu {

    private Date date;
    private Map<String, List<Food>> meals;

    public Menu(Date date) {
        this.date = date;
        this.meals = new HashMap<>();
    }

    public Date getDate() {
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