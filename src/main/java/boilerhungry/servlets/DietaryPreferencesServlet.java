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
import java.util.Collection;

public class DietaryPreferencesServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        RequestDispatcher view = req.getRequestDispatcher("dietarypreferences.jsp");
        ServletContext context = ServletContextHandler.getCurrentContext();
        Settings settings = (Settings) context.getAttribute("settings");
        Collection<String> exclusions = settings.getDietaryExclusions();
        Collection<String> preferences = settings.getDietaryPreferences();
        Collection<String> myFoods = settings.getMyFoods();
        req.setAttribute("myFoods",myFoods);
        req.setAttribute("exclusions", exclusions);
        req.setAttribute("preferences", preferences);
        res.setContentType("text/html");
        res.setCharacterEncoding("utf-8");
        view.forward(req, res);
    }

}