package boilerhungry.backend.servlets;

import boilerhungry.backend.DiningCourt;
import boilerhungry.backend.DiningCourtAPI;
import boilerhungry.backend.purdue.PurdueDiningCourtAPI;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("homepage.jsp");
        dispatcher.forward(req, res);
    }

}
