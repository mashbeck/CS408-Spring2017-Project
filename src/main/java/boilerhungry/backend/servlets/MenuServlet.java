package boilerhungry.backend.servlets;

import boilerhungry.backend.DiningCourt;
import boilerhungry.backend.DiningCourtAPI;
import boilerhungry.backend.Menu;
import boilerhungry.backend.purdue.PurdueDiningCourtAPI;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

public class MenuServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("application/json");
        DiningCourtAPI api = new PurdueDiningCourtAPI();
        res.setCharacterEncoding("utf-8");
        String diningCourtName = req.getParameter("diningCourt");
        String dateString = req.getParameter("date");
        LocalDate date = LocalDate.now();
        if (dateString != null && !dateString.isEmpty()) {
            date = LocalDate.parse(dateString, DiningCourt.DATE_TIME_FORMATTER);
        }
        Optional<DiningCourt> maybeDiningCourt = DiningCourt.getDiningCourt(api, diningCourtName);
        if (maybeDiningCourt.isPresent()) {
            DiningCourt diningCourt = maybeDiningCourt.get();
            Menu menu = diningCourt.getMenu(date);
            req.setAttribute("diningCourt", diningCourt);
            req.setAttribute("menu", menu);
            RequestDispatcher view = req.getRequestDispatcher("menu.jsp");
            view.forward(req, res);
        } else {
            res.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

}
