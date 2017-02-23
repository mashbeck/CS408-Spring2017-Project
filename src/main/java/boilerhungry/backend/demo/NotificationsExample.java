package boilerhungry.backend.demo;

import java.io.File;
import java.io.IOException;
import com.google.gson.Gson;
import boilerhungry.backend.*;
import boilerhungry.backend.purdue.PurdueDiningCourtAPI;
import java.util.*;

/**
 * Created by Lena Adel on 2/23/2017.
 */
public class NotificationsExample {

    public static void main(String [] args) throws IOException {
        Settings settings = Settings.load(new File("settings.json"));
        settings.getMyFoods().add("bacon");
        settings.getMyFoods().add("strawberries");
        settings.save();
        DiningCourtAPI api = new PurdueDiningCourtAPI();
        Notifications notifications = new Notifications();
        Map<String, List<ItemAppearance>> myFoodNotifications = notifications.myFoodAppearance(settings, api);
        for (Map.Entry<String, List<ItemAppearance>> entry : myFoodNotifications.entrySet()) {
            for(int i = 0; i < myFoodNotifications.size(); i++) {
                String searchFood = entry.getKey();
                String diningCourt = myFoodNotifications.get(searchFood).get(i).getDiningCourt();
                String meal = myFoodNotifications.get(searchFood).get(i).getMeal();
                String date = myFoodNotifications.get(searchFood).get(i).getDate();
                String station = myFoodNotifications.get(searchFood).get(i).getStation();
                System.out.println(searchFood + " is found in " + diningCourt + " dining court on "
                                    + date + " at " + station + " station.");
            }
        }
    }
}
