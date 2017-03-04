package boilerhungry.backend;

import java.io.IOException;
import java.util.*;

public class Notifications {

    private ItemFinder itemFinder;

    public Notifications(DiningCourtAPI api) {
        this.itemFinder = new ItemFinder(api);
    }

    public Map<String, List<UpcomingFood>> getMyFoodAppearances(Settings settings) throws IOException {
        Map<String, List<UpcomingFood>> upcomingFoods = new HashMap<>();
        for (String myFood : settings.getMyFoods()) {
            List<UpcomingFood> upcoming = itemFinder.searchUpComing(myFood);
            upcomingFoods.put(myFood, upcoming);
        }
        return upcomingFoods;
    }
}
