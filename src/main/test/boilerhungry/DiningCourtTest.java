package boilerhungry;

import boilerhungry.backend.DiningCourt;
import boilerhungry.backend.DiningCourtAPI;
import boilerhungry.backend.Menu;
import boilerhungry.backend.mock.MockDiningCourtAPI;
import boilerhungry.backend.purdue.PurdueDiningCourtAPI;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class DiningCourtTest {

    private static final DiningCourtAPI MOCK_API = new MockDiningCourtAPI();
    List<DiningCourt> diningCourts;

    @Before
    public void setup() throws IOException {
        diningCourts = DiningCourt.getDiningCourts(MOCK_API);
    }

    @Test
    public void OpeningDiningCourtMenuShouldGiveMenuForTheDay(){
        try {
            List<DiningCourt> diningCourts = DiningCourt.getDiningCourts(MOCK_API);
            for (DiningCourt diningCourt : diningCourts) {
                if (diningCourt.getName().equals("Earhart")) {
                    Menu menu = diningCourt.getMenu(new Date("02-02-2017"));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void ChangingDiningCourtShouldChangeTheMenu(){

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
