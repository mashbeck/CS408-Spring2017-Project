package boilerhungry.backend.demo;

import boilerhungry.backend.*;
import boilerhungry.backend.purdue.PurdueDiningCourtAPI;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class NotificationsExample {

    public static void main(String [] args) throws IOException {
        Settings settings = Settings.load(new File("settings.json"));
        settings.getMyFoods().add("pizza");
        settings.save();
        DiningCourtAPI api = new PurdueDiningCourtAPI();
        Notifications notifications = new Notifications(api, settings);
        Map<String, List<UpcomingFood>> myFoodNotifications = notifications.getMyFoodAppearances();
        for (Map.Entry<String, List<UpcomingFood>> entry : myFoodNotifications.entrySet()) {
            String name = entry.getKey();
            List<UpcomingFood> upcomingFoods = entry.getValue();
            for (UpcomingFood food : upcomingFoods) {
                for (ItemAppearance appearance : food.getAppearances()) {
                    String meal = appearance.getMeal().orElse("(unknown)");
                    String date = appearance.getDateTime().map(dt -> dt.format(DateTimeFormatter.ISO_LOCAL_DATE)).orElse("(unknown)");
                    String time = appearance.getDateTime().map(dt -> dt.format(DateTimeFormatter.ofPattern("h:mm a"))).orElse("(unknown)");
                    String station = appearance.getStation().orElse("(unknown)");
                    String diningCourt = appearance.getDiningCourt().orElse("(unknown)");
                    System.out.printf("%s is found in %s on %s at %s at the station %s during %s\n", name, diningCourt, date, time, station, meal);
                }
            }
        }
    }
}
