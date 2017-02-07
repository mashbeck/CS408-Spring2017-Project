package boilerhungry.backend;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Matthew on 2/4/2017.
 */
public class Menu {

    private String name;
    private Date date;
    private List<Food> breakfast;
    private List<Food> lunch;
    private List<Food> dinner;

    public Menu(String name, Date date) {
        this.name = name;
        this.date = date;
        this.breakfast = new ArrayList<>();
        this.lunch = new ArrayList<>();
        this.dinner = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
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
