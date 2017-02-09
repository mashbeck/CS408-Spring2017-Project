package boilerhungry;

import boilerhungry.backend.DiningCourt;
import boilerhungry.backend.DiningCourtAPI;
import boilerhungry.backend.Food;
import boilerhungry.backend.Menu;
import boilerhungry.backend.mock.MockDiningCourtAPI;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class DiningCourtTest {

    private DiningCourtAPI mockApi;
    //private List<DiningCourt> diningCourts;


    @Before
    public void setup() throws IOException {
        mockApi = new MockDiningCourtAPI();
       // diningCourts = DiningCourt.getDiningCourts(mockApi);
    }

    @Test
    public void OpeningDiningCourtMenuShouldGiveMenuForTheDay() throws IOException {
        Optional<DiningCourt> maybeEarhart = DiningCourt.getDiningCourt(mockApi, "Earhart");
        assertTrue("Earhart dining court should exist", maybeEarhart.isPresent());
        DiningCourt earhart = maybeEarhart.get();
        Menu menu = earhart.getMenu(LocalDate.parse("2017-02-07"));
        assertTrue("Earhart should serve breakfast", menu.getMealNames().contains("Breakfast"));
        assertTrue("Earhart should serve lunch", menu.getMealNames().contains("Lunch"));
        assertTrue("Earhart should serve dinner", menu.getMealNames().contains("Dinner"));

    }

    @Test
    public void ChangingDiningCourtShouldChangeTheMenu() throws IOException {
        Optional<DiningCourt> maybeEarhart = DiningCourt.getDiningCourt(mockApi, "Earhart");
        DiningCourt earhart = maybeEarhart.get();
        Menu earhartMenu = earhart.getMenu(LocalDate.parse("2017-02-07"));
        assertTrue("Earhart should serve breakfast", earhartMenu.getMealNames().contains("Lunch"));
        assertFalse("There should be data for Earhart lunch", earhartMenu.getMeal("Lunch").isEmpty());

        Optional<DiningCourt> maybeWiley = DiningCourt.getDiningCourt(mockApi, "Wiley");
        DiningCourt wiley = maybeWiley.get();
        Menu wileyMenu = wiley.getMenu(LocalDate.parse("2017-02-07"));
        // remove lunch from the mockdata for wiley to test if getMenu function works when we change the dining court
        assertFalse("Wiley shouldn't serve lunch", wileyMenu.getMealNames().contains("Lunch"));
        assertTrue("There shouldn't be data for Wiley Lunch",wileyMenu.getMeal("Lunch").isEmpty());
    }

    @Test
    public void ChangingDateShouldChangeTheMenu() throws IOException{
        Optional<DiningCourt> maybeEarhart = DiningCourt.getDiningCourt(mockApi, "Earhart");
        LocalDate currentDate = LocalDate.parse("2017-02-02");
        LocalDate newDate = LocalDate.parse("2017-02-07");
        DiningCourt earhart = maybeEarhart.get();
        boolean containsFood = false;

        Menu menuCurrentDate = earhart.getMenu(currentDate);
        List<Food> listFoodCurrent = menuCurrentDate.getMeal("Breakfast");
        //Breakfast should be served and should contain "Breakfast Polenta"
        assertTrue("Earhart should serve Breakfast for this current date", menuCurrentDate.getMealNames().contains("Breakfast"));
        for (Food food : listFoodCurrent) {
            if (food.getName().equals("Breakfast Polenta")) {
                containsFood = true;
            }
        }
        assertTrue("Earhart breakfast menu should serve Breakfast Polenta for this current date", containsFood);

        Menu menuNewDate = earhart.getMenu(newDate);
        List<Food> listFoodNew = menuNewDate.getMeal("Breakfast");
        Food MYOBreakfast = new Food("MYO Breakfast Bowl");
        //When date changed breakfast should be served for new date and should contain "MYO Breakfast Bowl"
        assertTrue("Earhart should serve Breakfast for this new changed date", menuNewDate.getMealNames().contains("Breakfast"));
        containsFood = false;
        for (Food food : listFoodNew) {
            if (food.getName().equals("MYO Breakfast Bowl")) {
                containsFood = true;
            }
        }
        assertTrue("Erahrt breakfast menu should serve MYO Breakfast Bowl for this new changed date", containsFood);
    }

    @Test
    public void ChangingDiningCourtAndChangingDateShouldChangeTheMenu(){

    }

    @Test
    public void OpeningDiningCourtMenuShouldGiveTheLocationAndOtherDetails() {

    }

    @Test
    public void ChangingDiningCourtShouldChangeTheLocationAndOtherDetails(){

    }

    @Test
    public void LookingForMenuOutsideOfDateRange() throws IOException {
        Optional<DiningCourt> maybeWiley = DiningCourt.getDiningCourt(mockApi, "Wiley");
        DiningCourt wiley = maybeWiley.get();
        Menu wileyMenu = wiley.getMenu(LocalDate.parse("2017-02-09"));
        assertFalse("Wiley shouldn't serve breakfast on incorrect date", wileyMenu.getMealNames().contains("Breakfast"));
        assertFalse("Wiley shouldn't serve lunch on incorrect date", wileyMenu.getMealNames().contains("Lunch"));
        assertFalse("Wiley shouldn't serve dinner on incorrect date", wileyMenu.getMealNames().contains("Dinner"));

    }

    @Test
    public void UnknownDiningCourtName() throws IOException{
        Optional<DiningCourt> maybeHungry = DiningCourt.getDiningCourt(mockApi, "Hungry");
        assertFalse("Hungry dining court shouldn't exist", maybeHungry.isPresent());
    }

    @Test
    //tests the right data is obtained when the user selects dining court and date
    public void OpenMenuCheckData() throws IOException {
        Optional<DiningCourt> maybeFord = DiningCourt.getDiningCourt(mockApi, "Ford");
        DiningCourt ford = maybeFord.get();
        Menu fordMenu = ford.getMenu(LocalDate.parse("2017-02-02"));
        boolean containsFood = false;
        List<Food> listFoodBreakfast = fordMenu.getMeal("Breakfast");
        for (Food food : listFoodBreakfast) {
            if (food.getName().equals("Blueberry Pancakes")) {
                containsFood = true;
            }
        }
        assertTrue("Ford breakfast food displayed is correct data", containsFood);

        List<Food> listFoodLunch = fordMenu.getMeal("Lunch");
        containsFood = false;
        for (Food food : listFoodLunch) {
            if (food.getName().equals("Apple Nut Cake")) {
                containsFood = true;
            }
        }
        assertTrue("Ford lunch food displayed is correct data", containsFood);

        List<Food> listFoodDinner = fordMenu.getMeal("Dinner");
        containsFood = false;
        for (Food food : listFoodDinner) {
            if (food.getName().equals("Cheese Pizza")) {
                containsFood = true;
            }
        }
        assertTrue("Ford dinner food displayed is correct data", containsFood);
    }

    @Test
    public void ChangingTimeShouldChangeTheMenu() throws IOException {
        Optional<DiningCourt> maybeWiley = DiningCourt.getDiningCourt(mockApi, "Wiley");
        DiningCourt wiley = maybeWiley.get();
        Menu wileyMenu = wiley.getMenu(LocalDate.parse("2017-02-02"));
        Boolean containsFood = false;
        List<Food> listFoodLunch = wileyMenu.getMeal("Lunch");
        for (Food food : listFoodLunch) {
            if (food.getName().equals("Broccoli Florets")) {
                containsFood = true;
            }
        }
        //test the correct data is obtained for the current time
        assertTrue("Ford food displayed is correct data for current time", containsFood);

        containsFood = false;
        List<Food> listFoodDinner = wileyMenu.getMeal("Dinner");
        for (Food food : listFoodDinner) {
            if (food.getName().equals("Tortilla Chips")) {
                containsFood = true;
            }
        }
        //test the correct data is obtained when time is changed
        assertTrue("Ford food displayed is correct data for changed time", containsFood);
    }

//    MyFoods List
//    Dietary Preferences
    

}
