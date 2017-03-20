package boilerhungry.servlets;

import boilerhungry.DiningCourt;
import boilerhungry.DiningCourtAPI;
import boilerhungry.Menu;
import boilerhungry.Settings;
import org.eclipse.jetty.servlet.ServletContextHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class MenuServlet extends RouteServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String food = req.getParameter("food");
        if (food == null ) {
            res.sendError(HttpServletResponse.SC_NOT_FOUND, "The requested object food was not found.");
        } else {
            ServletContext context = ServletContextHandler.getCurrentContext();
            Settings settings = (Settings) context.getAttribute("settings");
            Set<String> myFoods = settings.getMyFoods();
            if (myFoods.contains(food)) {
                myFoods.remove(food);
            } else {
                myFoods.add(food);
            }
            settings.save();
            res.setStatus(HttpServletResponse.SC_OK);
        }
    }
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("application/json");
        res.setCharacterEncoding("utf-8");
        List<String> routes = getRoutes(req.getPathInfo());
        if (routes.size() < 1 || routes.size() > 2) {
            res.sendError(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            try {
                String diningCourtName = routes.get(0);
                LocalDate date = routes.size() == 2 ?
                        LocalDate.parse(routes.get(1), DiningCourt.DATE_TIME_FORMATTER) :
                        LocalDate.now();
                ServletContext context = ServletContextHandler.getCurrentContext();
                Settings settings = (Settings) context.getAttribute("settings");
                Collection<String> myFoods = settings.getMyFoods();
                if(myFoods != null) {
                    req.setAttribute("myFoods",myFoods);
                } else {
                    res.sendError(HttpServletResponse.SC_NOT_FOUND);
                }
                DiningCourtAPI api = (DiningCourtAPI) getServletContext().getAttribute("api");
                Optional<DiningCourt> maybeDiningCourt = DiningCourt.getDiningCourt(api, diningCourtName);
                if (maybeDiningCourt.isPresent()) {
                    DiningCourt diningCourt = maybeDiningCourt.get();
                    Menu menu = diningCourt.getMenu(date);
                    req.setAttribute("diningCourt", diningCourt);
                    req.setAttribute("menu", menu);
                    RequestDispatcher view = req.getRequestDispatcher("/menu.jsp");
                    view.forward(req, res);
                } else {
                    res.sendError(HttpServletResponse.SC_NOT_FOUND);
                }
            } catch (DateTimeParseException ex) {
                res.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.toString());
            }
        }
    }

}
