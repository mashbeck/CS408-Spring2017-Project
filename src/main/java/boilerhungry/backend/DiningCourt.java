package boilerhungry.backend;

import java.util.ArrayList;

/**
 * Created by Matthew on 2/4/2017.
 */
public class DiningCourt {
    String name;
    private ArrayList<Food> breakfast;
    private ArrayList<Food> lunch;
    private ArrayList<Food> dinner;

    public DiningCourt(String name, ArrayList<Food> breakfast, ArrayList<Food> lunch, ArrayList<Food> dinner) {
        this.name = name;
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Food> getBreakfast() {
        return breakfast;
    }

    public ArrayList<Food> getLunch() {
        return lunch;
    }

    public ArrayList<Food> getDinner() {
        return dinner;
    }
}
