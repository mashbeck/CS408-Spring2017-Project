package boilerhungry.backend;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Matthew on 2/6/2017.
 */
public class Main {
    public static void main(String[] args) {

    }

    public ArrayList<DiningCourt> getDiningMenus(Date date) throws Exception{
        DateFormat format = new SimpleDateFormat("/MM-dd-yyyy");
        String day = format.format(date);
        String dCourtNames [] = {"Earhart", "Wiley", "Ford", "Hillenbrand", "Windsor" };
        ArrayList<DiningCourt> diningCourts = new ArrayList<DiningCourt>();
        for (int i = 0; i < dCourtNames.length; i++) {
            URL url;
            String inputLine;
            StringBuilder jsoninput = new StringBuilder();
            try {
                url = new URL("https://api.hfs.purdue.edu/menus/v2/locations/" + dCourtNames[i] + day);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(url.openStream()));
                while ((inputLine = in.readLine()) != null) {
                    jsoninput.append(inputLine);
                }
                in.close();
                inputLine = jsoninput.toString();
            } catch (java.io.IOException e) {
                e.printStackTrace();
                return null;
            }
            ArrayList<Food> breakfast = new ArrayList<Food>();
            ArrayList<Food> lunch = new ArrayList<Food>();
            ArrayList<Food> dinner = new ArrayList<Food>();

            JSONObject object = new JSONObject(inputLine);
            JSONArray meals = object.getJSONArray("Meals");

            for (int x = 0; x < meals.length(); x++) {
                String mealName = ((JSONObject) meals.get(x)).getString("Name");
                JSONArray stations = ((JSONObject) meals.get(x)).getJSONArray("Stations");
                for (int y = 0; y < stations.length(); y++) {
                    JSONArray items = ((JSONObject) stations.get(y)).getJSONArray("Items");
                    for (int z = 0; z < items.length(); z++) {
                        String name = ((JSONObject) items.get(z)).getString("Name");
                        boolean isVegetarian = ((JSONObject) items.get(z)).getBoolean("IsVegetarian");
                        JSONArray allergens = null;
                        if (((JSONObject) items.get(z)).has("Allergens")) {
                            allergens = ((JSONObject) items.get(z)).getJSONArray("Allergens");
                        }
                        boolean eggs = false;
                        boolean fish = false;
                        boolean gluten = false;
                        boolean milk = false;
                        boolean peanuts = false;
                        boolean shellfish = false;
                        boolean soy = false;
                        boolean treeNuts = false;
                        boolean wheat = false;
                        if (allergens != null) {
                            for (int a = 0; a < allergens.length(); a++) {
                                if (((JSONObject) allergens.get(a)).getString("Name").equals("Eggs")) {
                                    eggs = ((JSONObject) allergens.get(a)).getBoolean("Value");
                                }
                                if (((JSONObject) allergens.get(a)).getString("Name").equals("Fish")) {
                                    fish = ((JSONObject) allergens.get(a)).getBoolean("Value");
                                }
                                if (((JSONObject) allergens.get(a)).getString("Name").equals("Gluten")) {
                                    gluten = ((JSONObject) allergens.get(a)).getBoolean("Value");
                                }
                                if (((JSONObject) allergens.get(a)).getString("Name").equals("Milk")) {
                                    milk = ((JSONObject) allergens.get(a)).getBoolean("Value");
                                }
                                if (((JSONObject) allergens.get(a)).getString("Name").equals("Peanuts")) {
                                    peanuts = ((JSONObject) allergens.get(a)).getBoolean("Value");
                                }
                                if (((JSONObject) allergens.get(a)).getString("Name").equals("Shellfish")) {
                                    shellfish = ((JSONObject) allergens.get(a)).getBoolean("Value");
                                }
                                if (((JSONObject) allergens.get(a)).getString("Name").equals("Soy")) {
                                    soy = ((JSONObject) allergens.get(a)).getBoolean("Value");
                                }
                                if (((JSONObject) allergens.get(a)).getString("Name").equals("Tree Nuts")) {
                                    treeNuts = ((JSONObject) allergens.get(a)).getBoolean("Value");
                                }
                                if (((JSONObject) allergens.get(a)).getString("Name").equals("Wheat")) {
                                    wheat = ((JSONObject) allergens.get(a)).getBoolean("Value");
                                }
                            }
                        }
                        Food food = new Food(name, isVegetarian, eggs, fish, gluten, milk, peanuts, shellfish, soy, treeNuts,
                                wheat);
                        if (mealName.equals("Breakfast")) {
                            breakfast.add(food);
                        } else if (mealName.equals("Lunch")) {
                            lunch.add(food);
                        } else if (mealName.equals("Dinner")) {
                            dinner.add(food);
                        }
                    }
                }
            }
            DiningCourt diningCourt = new DiningCourt(dCourtNames[i],breakfast,lunch,dinner);
            diningCourts.add(diningCourt);
        }
        return diningCourts;
    }
}
