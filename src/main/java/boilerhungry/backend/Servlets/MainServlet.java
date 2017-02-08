package boilerhungry.backend.Servlets;

import boilerhungry.backend.DiningCourt;
import boilerhungry.backend.DiningCourtAPI;
import boilerhungry.backend.purdue.PurdueDiningCourtAPI;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;


public class MainServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        DiningCourtAPI api = new PurdueDiningCourtAPI();
        List<DiningCourt> diningCourts = null;
        try {
            diningCourts = DiningCourt.getDiningCourts(api);
        } catch (IOException e) {
            e.printStackTrace();
        }
        DiningCourt earhart=null;
        DiningCourt windsor=null;
        DiningCourt wiley=null;
        DiningCourt hillenbrand=null;
        DiningCourt ford=null;
        DiningCourt theGatheringPlace=null;
        for(DiningCourt dc: diningCourts){
            String name = dc.getName();
            switch(name){
                case "Earhart":
                    earhart = dc;
                    break;
                case "Windsor":
                    windsor = dc;
                    break;
                case "Wiley":
                    wiley = dc;
                    break;
                case "Hillenbrand":
                    hillenbrand = dc;
                    break;
                case "Ford":
                    ford = dc;
                    break;
                case "The Gathering Place":
                    theGatheringPlace = dc;
                    break;
            }
        }
        System.out.println(earhart.getName()+": "+earhart.getAddress());
        System.out.println(windsor.getName()+": "+wiley.getAddress());

    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        System.out.println("test");
    }

}
