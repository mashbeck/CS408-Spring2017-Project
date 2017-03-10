package boilerhungry.servlets;

import boilerhungry.*;
import boilerhungry.purdue.PurdueDiningCourtAPI;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HomeServlet extends HttpServlet {

    private DiningCourtAPI api = new PurdueDiningCourtAPI();
    private Notifications notifier = new Notifications(api);

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            Settings settings = Settings.load(new File("settings.json"));
            config.getServletContext().setAttribute("settings", settings);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        RequestDispatcher view = req.getRequestDispatcher("home.jsp");
        res.setContentType("text/html");
        res.setCharacterEncoding("utf-8");
        List<DiningCourt> diningCourts = DiningCourt.getDiningCourts(api);
        req.setAttribute("diningCourts", diningCourts);
        Settings settings = (Settings) getServletContext().getAttribute("settings");
        Map<String, List<UpcomingFood>> upcoming = notifier.getMyFoodAppearances(settings);
        List<String> notifications = upcoming.values().stream()
                .flatMap(Collection::stream)
                .flatMap(upcomingFood -> upcomingFood.getAppearances().stream())
                .sorted()
                .map(ItemAppearance::toString)
                .collect(Collectors.toList());
        req.setAttribute("notifications", notifications);
        view.forward(req, res);
    }

}
