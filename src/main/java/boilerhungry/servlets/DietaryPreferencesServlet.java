package boilerhungry.servlets;

import boilerhungry.Settings;
import org.eclipse.jetty.servlet.ServletContextHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

public class DietaryPreferencesServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        RequestDispatcher view = req.getRequestDispatcher("preferences.jsp");
        ServletContext context = ServletContextHandler.getCurrentContext();
        Settings settings = (Settings) context.getAttribute("settings");
        Set<String> exclusions = settings.getDietaryExclusions();
        Set<String> preferences = settings.getDietaryPreferences();
        Set<String> myFoods = settings.getMyFoods();
        req.setAttribute("myFoods",myFoods);
        req.setAttribute("exclusions", exclusions);
        req.setAttribute("preferences", preferences);
        res.setContentType("text/html");
        res.setCharacterEncoding("utf-8");
        view.forward(req, res);
    }

}