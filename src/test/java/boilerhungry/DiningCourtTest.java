package boilerhungry;

import boilerhungry.backend.*;
import boilerhungry.backend.mock.MockDiningCourtAPI;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;

public class DiningCourtTest {

    private DiningCourtAPI mockApi;
    private static final String DATA_DIR = "src/test/data/";

    @Before
    public void setup() throws IOException {
        mockApi = new MockDiningCourtAPI();
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
        Optional<DiningCourt> diningCourt = DiningCourt.getDiningCourt(mockApi, "Earhart");
        assertTrue("Earhart dining court should exist", diningCourt.isPresent());
        DiningCourt earhart = diningCourt.get();
        Menu earhartMenu = earhart.getMenu(LocalDate.parse("2017-02-07"));
        assertTrue("Earhart should serve breakfast", earhartMenu.getMealNames().contains("Lunch"));
        assertFalse("There should be data for Earhart lunch", earhartMenu.getMeal("Lunch").isEmpty());

        diningCourt = DiningCourt.getDiningCourt(mockApi, "Wiley");
        assertTrue("Wiley dining court should exist", diningCourt.isPresent());
        DiningCourt wiley = diningCourt.get();
        Menu wileyMenu = wiley.getMenu(LocalDate.parse("2017-02-07"));
        // remove lunch from the mockdata for wiley to test if getMenu function works when we change the dining court
        assertFalse("Wiley shouldn't serve lunch", wileyMenu.getMealNames().contains("Lunch"));
        assertTrue("There shouldn't be data for Wiley Lunch",wileyMenu.getMeal("Lunch").isEmpty());
    }

    @Test
    public void ChangingDateShouldChangeTheMenu() throws IOException{
        Optional<DiningCourt> maybeEarhart = DiningCourt.getDiningCourt(mockApi, "Earhart");
        assertTrue("Earhart dining court should exist", maybeEarhart.isPresent());
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
        //When date changed breakfast should be served for new date and should contain "MYO Breakfast Bowl"
        assertTrue("Earhart should serve Breakfast for this new changed date", menuNewDate.getMealNames().contains("Breakfast"));
        containsFood = false;
        for (Food food : listFoodNew) {
            if (food.getName().equals("MYO Breakfast Bowl")) {
                containsFood = true;
            }
        }
        assertTrue("Earhart breakfast menu should serve MYO Breakfast Bowl for this new changed date", containsFood);
    }

    @Test
    public void ChangingDiningCourtAndChangingDateShouldChangeTheMenu() throws IOException{
        //Breakfast Polenta is served at earhart, but not at Wiley
        Optional<DiningCourt> diningCourt = DiningCourt.getDiningCourt(mockApi, "Earhart");
        assertTrue("Earhart dining court should exist", diningCourt.isPresent());
        DiningCourt earhart = diningCourt.get();
        LocalDate currentDate = LocalDate.parse("2017-02-02");
        Menu earhartMenu = earhart.getMenu(currentDate);
        assertTrue("Earhart should serve breakfast", earhartMenu.getMealNames().contains("Lunch"));
        assertFalse("There should be data for Earhart lunch", earhartMenu.getMeal("Lunch").isEmpty());

        Menu menuCurrentDate = earhart.getMenu(currentDate);
        List<Food> listFoodCurrent = menuCurrentDate.getMeal("Breakfast");
        //Breakfast should be served and should contain "Breakfast Polenta"
        assertTrue("Earhart should serve Breakfast for this current date", menuCurrentDate.getMealNames().contains("Breakfast"));
        boolean containsFood = false;
        for (Food food : listFoodCurrent) {
            if (food.getName().equals("Breakfast Polenta")) {
                containsFood = true;
            }
        }

        assertTrue("Earhart breakfast menu should serve Breakfast Polenta for this current date", containsFood);


        LocalDate newDate = LocalDate.parse("2017-02-07");
        diningCourt = DiningCourt.getDiningCourt(mockApi, "Wiley");
        assertTrue("Wiley dining court should exist", diningCourt.isPresent());
        DiningCourt wiley = diningCourt.get();

        Menu menuNewDate = wiley.getMenu(newDate);
        List<Food> listFoodNew = menuNewDate.getMeal("Breakfast");
        //When date changed breakfast should be served for new date and should contain "MYO Breakfast Bowl"
        assertTrue("Wiley should serve Breakfast for this new changed date", menuNewDate.getMealNames().contains("Breakfast"));
        containsFood = false;
        for (Food food : listFoodNew) {
            if (food.getName().equals("Sausage Links")) {
                containsFood = true;
            }
        }
        assertTrue("Wiley breakfast menu should serve Sausage Links for this new changed date", containsFood);
    }

    @Test
    public void OpeningDiningCourtMenuShouldGiveTheLocationAndOtherDetails() throws IOException {
        Optional<DiningCourt> maybeWiley = DiningCourt.getDiningCourt(mockApi, "Wiley");
        assertTrue("Wiley dining court should exist", maybeWiley.isPresent());
        DiningCourt wiley = maybeWiley.get();
        String location = wiley.getAddress();
        String name = wiley.getName();
        Menu menu = wiley.getMenu(LocalDate.parse("2017-02-07"));
        assertNotNull("location should not be null", location);
        assertNotNull("name should not be null", name);
        assertTrue("Wiley should serve breakfast", menu.getMealNames().contains("Breakfast"));
    }

    @Test
    public void ChangingDiningCourtShouldChangeTheLocationAndOtherDetails() throws IOException{
        Optional<DiningCourt> diningCourt = DiningCourt.getDiningCourt(mockApi, "Wiley");
        assertTrue("Wiley dining court should exist", diningCourt.isPresent());
        DiningCourt wiley = diningCourt.get();
        String location = wiley.getAddress();
        String name = wiley.getName();
        Menu menu = wiley.getMenu(LocalDate.parse("2017-02-07"));
        assertEquals("498 North Martin Jischke Drive, West Lafayette, IN 47906-4277", location);
        assertNotNull("name should not be null", name);
        assertTrue("Wiley should serve breakfast", menu.getMealNames().contains("Breakfast"));

        diningCourt = DiningCourt.getDiningCourt(mockApi, "Earhart");
        assertTrue("Earhart dining court should exist", diningCourt.isPresent());
        DiningCourt earhart = diningCourt.get();
        location = earhart.getAddress();
        name = earhart.getName();
        menu = earhart.getMenu(LocalDate.parse("2017-02-07"));
        assertEquals("1275 First Street, West Lafayette, IN 47906-4231", location);
        assertNotNull("name should not be null", name);
        assertTrue("Earhart should serve breakfast", menu.getMealNames().contains("Breakfast"));
    }

    @Test
    public void LookingForMenuOutsideOfDateRange() throws IOException {
        Optional<DiningCourt> maybeWiley = DiningCourt.getDiningCourt(mockApi, "Wiley");
        assertTrue("Wiley dining court should exist", maybeWiley.isPresent());
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
        assertTrue("Ford dining court should exist", maybeFord.isPresent());
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
        assertTrue("Wiley dining court should exist", maybeWiley.isPresent());
        DiningCourt wiley = maybeWiley.get();
        Menu wileyMenu = wiley.getMenu(LocalDate.parse("2017-02-02"));
        boolean containsFood = false;
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

    @Test
    public void MyFoodsSaveAndLoad() throws IOException {
        Settings settings = Settings.load(new File(DATA_DIR + "settings-save.json"));
        assertNotNull("settings should not be null", settings);
        HashSet<String> myFoods = settings.getMyFoods();
        assertNotNull("myFoods should not be null", myFoods);
        myFoods.add("Blueberry");
        myFoods.add("Pizza");
        myFoods.add("Corn bread");
        myFoods.add("Chili");
        //add duplicate
        myFoods.add("Chili");
        settings.save();
        //reload settings after saving
        settings = Settings.load(new File(DATA_DIR + "settings-save.json"));
        myFoods = settings.getMyFoods();
        assertNotNull("myFoods should not be null", myFoods);
        assertTrue("MyFoods contain Blueberry after added and saved to MyFoods", myFoods.contains("Blueberry"));
        assertTrue("MyFoods contain Pizza after added and saved to MyFoods", myFoods.contains("Pizza"));
        assertTrue("MyFoods contain Corn bread after added and saved to MyFoods", myFoods.contains("Corn bread"));
        assertTrue("MyFoods contain Chili after added and saved to MyFoods", myFoods.contains("Chili"));
    }

    @Test
    public void DietaryPreferencesSaveAndLoad() throws IOException {
        Settings settings = Settings.load(new File(DATA_DIR + "settings-save.json"));
        assertNotNull("settings should not be null", settings);
        DietaryPreferences preferences = settings.getDietaryPreferences();
        assertNotNull("dietary preferences should not be null", preferences);
        preferences.setNoFish(true);
        preferences.setNoShellfish(true);
        preferences.setNoPeanuts(true);
        preferences.setVegetarian(false);
        preferences.setNoEggs(false);
        settings.save();
        //reload setting after saving
        settings = Settings.load(new File(DATA_DIR + "settings-save.json"));
        assertNotNull("settings should not be null", settings);
        preferences = settings.getDietaryPreferences();
        assertNotNull("dietary preferences should not be null", preferences);
        assertTrue("dietaryPreferences isNoFish should be true", preferences.isNoFish());
        assertTrue("dietaryPreferences isNoShellfish should be true", preferences.isNoShellfish());
        assertTrue("dietaryPreferences isNoPeanuts should be true", preferences.isNoPeanuts());
        assertFalse("dietaryPreferences isNoEggs should be false", preferences.isNoEggs());
        assertFalse("dietaryPreferences isVegeterian should be false", preferences.isVegetarian());
    }

    @Test
    public void TestSettingsLoad() throws IOException {
        Settings settings = Settings.load(new File(DATA_DIR + "settings-load.json"));
        assertNotNull("settings should not be null", settings);
        DietaryPreferences preferences = settings.getDietaryPreferences();
        assertNotNull("dietary preferences should not be null", preferences);
        assertTrue("dietaryPreferences.vegetarian should be true", preferences.isVegetarian());
        assertTrue("dietaryPreferences.noEggs should be true", preferences.isNoEggs());
        assertTrue("dietaryPreferences.noFish should be true", preferences.isNoFish());
        assertFalse("dietaryPreferences.noGluten should be false", preferences.isNoGluten());
        assertFalse("dietaryPreferences.noMilk should be false", preferences.isNoMilk());
        assertFalse("dietaryPreferences.noPeanuts should be false", preferences.isNoPeanuts());
        assertTrue("dietaryPreferences.noShellfish should be true", preferences.isNoShellfish());
        assertTrue("dietaryPreferences.noSoy should be true", preferences.isNoSoy());
        assertTrue("dietaryPreferences.noWheat should be true", preferences.isNoWheat());
        Set<String> myFoods = settings.getMyFoods();
        assertNotNull("myFoods should not be null", myFoods);
        assertTrue("myFoods should contain 'bacon'", myFoods.contains("bacon"));
        assertTrue("myFoods should contain 'strawberries'", myFoods.contains("strawberries"));
        assertFalse("myFoods should not contain 'lettuce'", myFoods.contains("lettuce"));
    }


}
