package boilerhungry.servlets;

import boilerhungry.Menu;
import boilerhungry.DiningCourt;
import boilerhungry.DiningCourtAPI;
import boilerhungry.purdue.PurdueDiningCourtAPI;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

public class MenuServlet extends RouteServlet {
    static boolean testing = true;

    private DiningCourtAPI api = new PurdueDiningCourtAPI();

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
                LocalDate date;
                if(testing){
                    date= LocalDate.of(2017,3,6);
                }
                else{
                    date = routes.size() == 2 ?
                        LocalDate.parse(routes.get(1), DiningCourt.DATE_TIME_FORMATTER) :
                        LocalDate.now();
                }
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
