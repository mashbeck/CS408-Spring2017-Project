package boilerhungry.backend;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by eric on 2/6/17.
 */
public class DiningCourt {

    private String name;
    private String address;
    //private DiningCourtHours hours;
    private DiningCourtAPI api;
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MM-dd-yyy");

    public DiningCourt(DiningCourtAPI api, String name, String address) {
        this.api = api;
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Menu getMenu(LocalDate date) throws IOException {
        String day = DATE_TIME_FORMATTER.format(date);
        URL url = new URL("https://api.hfs.purdue.edu/menus/v2/locations/" + this.name + "/" + day);
        Menu menu = new Menu(date);
        JSONObject root = api.getJSON(url);
        JSONArray meals = root.getJSONArray("Meals");
        for (int i = 0; i < meals.length(); i++) {
            JSONObject meal = meals.getJSONObject(i);
            String mealName = meal.getString("Name");
            JSONObject mealHours = meal.getJSONObject("Hours");
            String startTime = mealHours.getString("StartTime");
            String endTime = mealHours.getString("EndTime");
            Hours hours = new Hours(startTime, endTime);
            JSONArray stations = meal.getJSONArray("Stations");
            for (int j = 0; j < stations.length(); j++) {
                JSONObject station = stations.getJSONObject(j);
                String stationName = station.getString("Name");
                JSONArray items = station.getJSONArray("Items");
                for (int k = 0; k < items.length(); k++) {
                    JSONObject item = items.getJSONObject(k);
                    String foodName = item.getString("Name");
                    Food food = new Food(foodName);
                    food.setStation(stationName);
                    food.setVegetarian(item.getBoolean("IsVegetarian"));
                    if (item.has("Allergens")) {
                        JSONArray allergens = item.getJSONArray("Allergens");
                        for (int a = 0; a < allergens.length(); a++) {
                            JSONObject allergen = allergens.getJSONObject(a);
                            boolean value = allergen.getBoolean("Value");
                            switch (allergen.getString("Name")) {
                                case "Eggs":
                                    food.setEggs(value);
                                    break;
                                case "Fish":
                                    food.setFish(value);
                                    break;
                                case "Gluten":
                                    food.setGluten(value);
                                    break;
                                case "Milk":
                                    food.setMilk(value);
                                    break;
                                case "Peanuts":
                                    food.setPeanuts(value);
                                    break;
                                case "Shellfish":
                                    food.setShellfish(value);
                                    break;
                                case "Soy":
                                    food.setSoy(value);
                                    break;
                                case "Tree Nuts":
                                    food.setTreeNuts(value);
                                    break;
                                case "Wheat":
                                    food.setWheat(value);
                                    break;
                            }
                        }
                    }
                    menu.getMeals()
                        .computeIfAbsent(mealName, foods -> new Meal(mealName, hours))
                        .getFoods()
                        .add(food);
                }
            }
        }
        return menu;
    }

    public static List<DiningCourt> getDiningCourts(DiningCourtAPI api) throws IOException {
        List<DiningCourt> diningCourts = new ArrayList<>();
        JSONObject root = api.getJSON(new URL("https://api.hfs.purdue.edu/menus/v2/locations/"));
        JSONArray locations = root.getJSONArray("Location");
        for (int i = 0; i < locations.length(); i++) {
            JSONObject location = locations.getJSONObject(i);
            String name = location.getString("Name");
            String address = api.getAddressString(location);
            // TODO get hours
            diningCourts.add(new DiningCourt(api, name, address));
        }
        return diningCourts;
    }

    public static Optional<DiningCourt> getDiningCourt(DiningCourtAPI api, String name) throws IOException {
        return getDiningCourts(api).stream()
                .filter(dc -> dc.getName().equals(name))
                .findFirst();
    }

}
