package boilerhungry.backend.Servlets;

import boilerhungry.backend.DiningCourt;
import boilerhungry.backend.DiningCourtAPI;
import boilerhungry.backend.purdue.PurdueDiningCourtAPI;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
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

        for(DiningCourt dc: diningCourts){
            String name = dc.getName();
            switch(name){
                case "Earhart":
                    getServletContext().setAttribute("Earhart",dc);
                    break;
                case "Windsor":
                    getServletContext().setAttribute("Windsor",dc);
                    break;
                case "Wiley":
                    getServletContext().setAttribute("Wiley",dc);
                    break;
                case "Hillenbrand":
                    getServletContext().setAttribute("Hillenbrand",dc);
                    break;
                case "Ford":
                    getServletContext().setAttribute("Ford",dc);
                    break;
                case "The Gathering Place":
                    getServletContext().setAttribute("The Gathering Place",dc);
                    break;
            }
        }

    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        System.out.println("test");
    }

}
