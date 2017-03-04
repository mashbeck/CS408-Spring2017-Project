package boilerhungry.backend.demo;

import boilerhungry.backend.*;
import boilerhungry.backend.purdue.PurdueDiningCourtAPI;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class NotificationsExample {

    public static void main(String [] args) throws IOException {
        Settings settings = Settings.load(new File("settings.json"));
        settings.getMyFoods().add("pizza");
        settings.save();
        DiningCourtAPI api = new PurdueDiningCourtAPI();
        Notifications notifications = new Notifications(api);
        Map<String, List<UpcomingFood>> myFoodNotifications = notifications.getMyFoodAppearances(settings);
        for (Map.Entry<String, List<UpcomingFood>> entry : myFoodNotifications.entrySet()) {
            List<UpcomingFood> upcomingFoods = entry.getValue();
            for (UpcomingFood food : upcomingFoods) {
                for (ItemAppearance appearance : food.getAppearances()) {
                    System.out.println(appearance);
                }
            }
        }
    }
}
