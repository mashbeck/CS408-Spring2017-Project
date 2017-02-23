package boilerhungry.backend;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class DiningCourt {

    private String name;
    private String address;
    private DiningCourtAPI api;
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MM-dd-yyy");
    private Map<String, Menu> menuCache = new ConcurrentHashMap<>();

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

    public Menu getMenu(final LocalDate date) throws IOException {
        final String day = DATE_TIME_FORMATTER.format(date);
        final String base = "https://api.hfs.purdue.edu/menus/v2";
        final String endpoint = "/locations/" + this.name + "/" + day;
        if (menuCache.containsKey(endpoint)) {
            return menuCache.get(endpoint);
        } else {
            URL url = new URL(base + endpoint);
            Menu menu = new Menu(date);
            JSONObject root = api.getJSON(url);
            JSONArray meals = root.getJSONArray("Meals");
            for (int i = 0; i < meals.length(); i++) {
                JSONObject meal = meals.getJSONObject(i);
                String mealName = meal.getString("Name");
                final Hours hours = getHours(meal);
                JSONArray stations = meal.getJSONArray("Stations");
                for (int j = 0; j < stations.length(); j++) {
                    JSONObject station = stations.getJSONObject(j);
                    String stationName = station.getString("Name");
                    JSONArray items = station.getJSONArray("Items");
                    for (int k = 0; k < items.length(); k++) {
                        JSONObject item = items.getJSONObject(k);
                        String foodName = item.getString("Name");
                        Food food = new Food(foodName, stationName);
                        if (item.has("Allergens")) {
                            JSONArray allergenArray = item.getJSONArray("Allergens");
                            food.addAllergens(getAllergens(allergenArray));
                        }
                        menu.getMeals()
                                .computeIfAbsent(mealName, foods -> new Meal(mealName, hours))
                                .getFoods()
                                .add(food);
                    }
                }
            }
            menuCache.putIfAbsent(endpoint, menu);
            return menu;
        }
    }

    private Map<String, Boolean> getAllergens(JSONArray arr) {
        Map<String, Boolean> allergens = new HashMap<>();
        for (int a = 0; a < arr.length(); a++) {
            JSONObject allergen = arr.getJSONObject(a);
            String name = allergen.getString("Name");
            boolean value = allergen.getBoolean("Value");
            allergens.put(name, value);
        }
        return allergens;
    }

    private Hours getHours(JSONObject meal) {
        if (meal.optJSONObject("Hours") != null) {
            JSONObject mealHoursObj = meal.getJSONObject("Hours");
            String startTime = mealHoursObj.getString("StartTime");
            String endTime = mealHoursObj.getString("EndTime");
            return new Hours(startTime, endTime);
        } else {
            return new Hours();
        }
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
