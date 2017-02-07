package boilerhungry.backend;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by eric on 2/6/17.
 */
public class DiningCourt extends DiningCourtAPI {

    private String name;
    private String address;

    public DiningCourt(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Menu getMenu(Date date) throws IOException {
        DateFormat format = new SimpleDateFormat("/MM-dd-yyyy");
        String day = format.format(date);
        URL url = new URL("https://api.hfs.purdue.edu/menus/v2/locations/" + this.name + day);
        Menu menu = new Menu(this.name, date);
        JSONObject root = getJSON(url);
        JSONArray meals = root.getJSONArray("Meals");
        for (int i = 0; i < meals.length(); i++) {
            JSONObject meal = meals.getJSONObject(i);
            String mealName = meal.getString("Name");
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
                        .computeIfAbsent(mealName, foods -> new ArrayList<>())
                        .add(food);
                }
            }
        }
        return menu;
    }

    public static List<DiningCourt> getDiningCourts() throws IOException {
        List<DiningCourt> diningCourts = new ArrayList<>();
        JSONObject root = getJSON(new URL("https://api.hfs.purdue.edu/menus/v2/locations/"));
        JSONArray locations = root.getJSONArray("Location");
        for (int i = 0; i < locations.length(); i++) {
            JSONObject location = locations.getJSONObject(i);
            String name = location.getString("Name");
            String address = getAddressString(location);
            // TODO get hours
            // TODO the menu needs to be fetched
            diningCourts.add(new DiningCourt(name, address));
        }
        return diningCourts;
    }

    private static String getAddressString(JSONObject location) {
        StringBuilder res = new StringBuilder();
        JSONObject address = location.getJSONObject("Address");
        res.append(address.getString("Street")).append(", ");
        res.append(address.getString("City")).append(", ");
        res.append(address.getString("State")).append(" ");
        res.append(address.getString("ZipCode"));
        return res.toString();
    }

}
