package boilerhungry.servlets;

import boilerhungry.Settings;
import com.google.gson.Gson;
import org.eclipse.jetty.servlet.ServletContextHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

public class PreferencesServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String food = req.getParameter("food");
        String foodType = req.getParameter("foodType");
        if (food == null) {
            res.sendError(HttpServletResponse.SC_NOT_FOUND, "The requested object food was not found.");
        } else if (foodType == null) {
            res.sendError(HttpServletResponse.SC_NOT_FOUND, "The requested object foodType was not found.");
        } else {
            ServletContext context = ServletContextHandler.getCurrentContext();
            Settings settings = (Settings) context.getAttribute("settings");
            Set<String> pref;
            switch (foodType) {
                case "exclusion":
                    pref = settings.getDietaryExclusions();
                    break;
                case "preference":
                    pref = settings.getDietaryPreferences();
                    break;
                default:
                    res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid foodType provided.");
                    return;
            }
            if (pref.contains(food)) {
                pref.remove(food);
            } else {
                pref.add(food);
            }
            settings.save();
            res.setStatus(HttpServletResponse.SC_OK);
        }
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        RequestDispatcher view = req.getRequestDispatcher("preferences.jsp");
        ServletContext context = ServletContextHandler.getCurrentContext();
        Settings settings = (Settings) context.getAttribute("settings");
        Set<String> exclusions = settings.getDietaryExclusions();
        Set<String> preferences = settings.getDietaryPreferences();
        Set<String> myFoods = settings.getMyFoods();
        req.setAttribute("myFoods", myFoods);
        req.setAttribute("exclusions", exclusions);
        req.setAttribute("preferences", preferences);
        req.setAttribute("preferencesArr", new Gson().toJson(preferences));
        req.setAttribute("exclusionsArr", new Gson().toJson(exclusions));
        res.setContentType("text/html");
        res.setCharacterEncoding("utf-8");
        view.forward(req, res);
    }

}