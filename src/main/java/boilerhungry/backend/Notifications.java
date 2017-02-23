package boilerhungry.backend;

import boilerhungry.backend.purdue.PurdueDiningCourtAPI;

import java.io.IOException;
import java.util.*;

/**
 * Created by Lena Adel on 2/22/2017.
 */
public class Notifications {

    private Map<String, List<ItemAppearance>> myFoodNotifications;

    public Notifications() {
        myFoodNotifications = new HashMap<>();
    }
    public Map<String, List<ItemAppearance>> myFoodAppearance(Settings setting, DiningCourtAPI api) throws IOException{
        //function for returning itemAppearnces for each food
        Collection<String> myFood = setting.getMyFoods();
        Boolean hasNext = myFood.iterator().hasNext();
        while (hasNext) {
            String food = myFood.iterator().next();
            SearchFood searchFood = new SearchFood(api, food);
            List<ItemAppearance> listAppearances = new ArrayList<>();
            listAppearances = searchFood.searchUpComing(api, food);
            myFoodNotifications.put(food, listAppearances);
        }
        return  myFoodNotifications;
    }
}
