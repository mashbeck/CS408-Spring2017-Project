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

/**
 * Created by flekaty123 on 23/03/17.
 */
public class MyFoodsServlet extends HttpServlet{
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
        RequestDispatcher view = req.getRequestDispatcher("myFoods.jsp");
        ServletContext context = ServletContextHandler.getCurrentContext();
        Settings settings = (Settings) context.getAttribute("settings");
        Set<String> myFoods = settings.getMyFoods();
        req.setAttribute("myFoods",myFoods);
        res.setContentType("text/html");
        res.setCharacterEncoding("utf-8");
        view.forward(req, res);
    }

}
