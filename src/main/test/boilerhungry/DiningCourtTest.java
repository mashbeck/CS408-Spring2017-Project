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

import static org.junit.Assert.assertTrue;

public class DiningCourtTest {

    private DiningCourtAPI mockApi;
    private List<DiningCourt> diningCourts;

    @Before
    public void setup() throws IOException {
        mockApi = new MockDiningCourtAPI();
        diningCourts = DiningCourt.getDiningCourts(mockApi);
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
        assertTrue("Earhart dining court should exist", maybeEarhart.isPresent());
        DiningCourt earhart = maybeEarhart.get();
        Menu menu = earhart.getMenu(LocalDate.parse("2017-02-07"));
        assertTrue("Earhart should serve breakfast", menu.getMealNames().contains("Breakfast"));
//        List<Food> foodList = menu.
//        assertTrue("Earhart should have blah blah in ");
        assertTrue("Earhart should serve lunch", menu.getMealNames().contains("Lunch"));
        assertTrue("Earhart should serve dinner", menu.getMealNames().contains("Dinner"));

        Optional<DiningCourt> maybeWiley = DiningCourt.getDiningCourt(mockApi, "Wiley");
        assertTrue("Wiley dining court should exist", maybeWiley.isPresent());
        DiningCourt wiley = maybeWiley.get();
        menu = wiley.getMenu(LocalDate.parse("2017-02-07"));
        assertTrue("Wiley should serve breakfast", menu.getMealNames().contains("Breakfast"));
        assertTrue("Wiley should serve lunch", menu.getMealNames().contains("Lunch"));
        assertTrue("Wiley should serve dinner", menu.getMealNames().contains("Dinner"));
    }

    @Test
    public void ChangingDateShouldChangeTheMenu(){

    }

    @Test
    public void ChangingDiningCourtAndChangingDateShouldChangeTheMenu(){

    }

    @Test
    public void ChangingDiningCourtShouldChangeTheLocationAndOtherDetails(){

    }

    @Test
    public void LookingForMenuOutsideOfRange(){

    }

    @Test
    public void UnknownDiningCourtName(){


    }

//    MyFoods List
//    Dietary Preferences
    

}
