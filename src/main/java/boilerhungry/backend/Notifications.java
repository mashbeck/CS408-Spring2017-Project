package boilerhungry.backend;

import java.io.IOException;
import java.util.*;

public class Notifications {

    private Map<String, List<UpcomingFood>> upcomingFoods;
    private Settings settings;
    private ItemFinder searchFood;

    public Notifications(DiningCourtAPI api, Settings settings) {
        this.upcomingFoods = new HashMap<>();
        this.searchFood = new ItemFinder(api);
        this.settings = settings;
    }
    public Map<String, List<UpcomingFood>> getMyFoodAppearances() throws IOException {
        for (String myFood : settings.getMyFoods()) {
            List<UpcomingFood> upcoming = searchFood.searchUpComing(myFood);
            upcomingFoods.put(myFood, upcoming);
        }
        return upcomingFoods;
    }
}
