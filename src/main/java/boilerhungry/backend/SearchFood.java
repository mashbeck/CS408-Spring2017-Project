package boilerhungry.backend;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lena Adel on 2/22/2017.
 */
public class SearchFood {
    private String searchFood;
    private DiningCourtAPI api;


    public SearchFood(DiningCourtAPI api, String searchFood) {
        this.api = api;
        this.searchFood = searchFood;
    }

    public  String getSearchFood() {
        return searchFood;
    }

    public static List<ItemAppearance> searchUpComing(DiningCourtAPI api, String searchFood) throws IOException{
        URL url = new URL("https://api.hfs.purdue.edu/menus/v2/items/searchUpcoming/" + searchFood);
        JSONObject root = api.getJSON(url);
        //System.out.println(url);
        //System.out.println(root);
        JSONArray itemAppearances = root.getJSONArray("ItemAppearance");
        List<ItemAppearance> listAppearances = new ArrayList<>();
        for(int i = 0; i < itemAppearances.length(); i++) {
            JSONObject item = itemAppearances.getJSONObject(i);
            String diningCourt = item.getString("Location");
            String station = item.getString("Station");
            String meal = item.getString("Meal");
            String date = item.getString("Date");
            ItemAppearance itemAppearance = new ItemAppearance(searchFood);
            itemAppearance.setDiningCourt(diningCourt);
            itemAppearance.setDate(date);
            itemAppearance.setMeal(meal);
            itemAppearance.setSearchFood(searchFood);
            itemAppearance.setStation(station);
            listAppearances.add(itemAppearance);
        }
        return listAppearances;
    }

}
