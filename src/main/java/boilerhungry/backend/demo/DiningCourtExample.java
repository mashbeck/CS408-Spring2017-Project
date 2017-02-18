package boilerhungry.backend.demo;

import boilerhungry.backend.*;
import boilerhungry.backend.purdue.PurdueDiningCourtAPI;
import com.google.gson.Gson;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by eric on 2/6/17.
 */
public class DiningCourtExample {

    public static void main(String[] args) throws IOException {
        Gson gson = new Gson();
        DiningCourtAPI api = new PurdueDiningCourtAPI();
        List<DiningCourt> diningCourts = DiningCourt.getDiningCourts(api);
        for (DiningCourt diningCourt : diningCourts) {
            System.out.println("Dining Court: " + diningCourt.getName());
            System.out.println(diningCourt.getAddress());
            System.out.println();
            try {
                Menu menu = diningCourt.getMenu(LocalDate.now());
                System.out.println("Meals Served: (" + menu.getMealNames().stream().collect(Collectors.joining(", ")) + ")");
                String mealName = "Breakfast";
                menu.getMeal(mealName)
                    .map(Meal::getFoods)
                    .ifPresent(breakfast -> {
                        System.out.println(mealName + " Menu (top 3):");
                        for (int i = 0; i < breakfast.size() && i < 3; i++) {
                            Food food = breakfast.get(i);
                            String json = gson.toJson(food);
                            System.out.println(json);
                        }
                        System.out.println();
                    });
            } catch (RuntimeException ex) {
                System.out.println("Dining court menu not available");
            }
        }
    }

}
