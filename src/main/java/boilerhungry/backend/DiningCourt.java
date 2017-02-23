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
    private static final Map<String, Object> cache = new ConcurrentHashMap<>();

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
        final String base = "https://api.hfs.purdue.edu/menus/v2/";
        final String endpoint = String.format("locations/%s/%s", this.name, day);
        if (cache.containsKey(endpoint)) {
            return (Menu) cache.get(endpoint);
        } else {
            URL url = new URL(base + endpoint);
            final Menu menu = new Menu(date);
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
            cache.putIfAbsent(endpoint, menu);
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
        final List<DiningCourt> diningCourts = new ArrayList<>();
        final String base = "https://api.hfs.purdue.edu/menus/v2/";
        final String endpoint = "locations/";
        if (cache.containsKey(endpoint)) {
            return (List<DiningCourt>) cache.get(endpoint);
        } else {
            JSONObject root = api.getJSON(new URL(base + endpoint));
            JSONArray locations = root.getJSONArray("Location");
            for (int i = 0; i < locations.length(); i++) {
                JSONObject location = locations.getJSONObject(i);
                String name = location.getString("Name");
                String address = api.getAddressString(location);
                // TODO get hours
                diningCourts.add(new DiningCourt(api, name, address));
            }
            cache.putIfAbsent(endpoint, diningCourts);
            return diningCourts;
        }
    }

    public static Optional<DiningCourt> getDiningCourt(DiningCourtAPI api, String name) throws IOException {
        return getDiningCourts(api).stream()
                .filter(dc -> dc.getName().equals(name))
                .findFirst();
    }

}
