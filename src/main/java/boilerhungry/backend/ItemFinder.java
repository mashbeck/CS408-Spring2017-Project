package boilerhungry.backend;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ItemFinder extends JsonParser {

    private DiningCourtAPI api;

    public ItemFinder(DiningCourtAPI api) {
        this.api = api;
    }

    public List<UpcomingFood> searchUpComing(String searchFood) throws IOException {
        URL url = new URL("https://api.hfs.purdue.edu/menus/v2/items/searchUpcoming/" + searchFood);
        JSONObject root = api.getJSON(url);
        JSONArray results = root.getJSONArray("Results");
        List<UpcomingFood> upcomingFoods = new ArrayList<>();
        for(int i = 0; i < results.length(); i++) {
            JSONObject item = results.getJSONObject(i);
            String name = item.getString("Name");
            UpcomingFood upcomingFood = new UpcomingFood(name);
            JSONObject schedule = item.getJSONObject("ItemSchedule");
            JSONArray appearances = schedule.getJSONArray("ItemAppearance");
            for (int j = 0; j < appearances.length(); j++) {
                JSONObject appearance = appearances.getJSONObject(j);
                ItemAppearance itemAppearance = new ItemAppearance(searchFood);
                get(appearance, "Location", String.class).ifPresent(itemAppearance::setDiningCourt);
                get(appearance, "Meal", String.class).ifPresent(itemAppearance::setMeal);
                get(appearance, "Date", String.class).ifPresent(itemAppearance::setDate);
                get(appearance, "Station", String.class).ifPresent(itemAppearance::setStation);
                upcomingFood.addAppearance(itemAppearance);
            }
            upcomingFoods.add(upcomingFood);
        }
        return upcomingFoods;
    }

}
