package boilerhungry.backend;

import java.util.List;

/**
 * Created by Matthew on 2/4/2017.
 */
public class DiningCourt {

    private String name;
    private List<Food> breakfast;
    private List<Food> lunch;
    private List<Food> dinner;

    public DiningCourt(String name, List<Food> breakfast, List<Food> lunch, List<Food> dinner) {
        this.name = name;
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
    }

    public String getName() {
        return name;
    }

    public List<Food> getBreakfast() {
        return breakfast;
    }

    public List<Food> getLunch() {
        return lunch;
    }

    public List<Food> getDinner() {
        return dinner;
    }
}
