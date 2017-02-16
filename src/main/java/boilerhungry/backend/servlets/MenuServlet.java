package boilerhungry.backend.servlets;

import boilerhungry.backend.DiningCourt;
import boilerhungry.backend.Food;
import boilerhungry.backend.Menu;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;


public class MenuServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        String input;
        try{
             input = request.getParameter("viewMenu");
        }
        catch(NullPointerException e){
            System.out.println("requested parameter is null");
            return;
        }
        DiningCourt diningCourt = (DiningCourt) getServletContext().getAttribute(input);
        //IF DININGCOURT iS NULL...
        StringBuilder ret = new StringBuilder();
        try {
            Menu menu = diningCourt.getMenu(LocalDate.now());
                for(Iterator iterator = menu.getMealNames().iterator(); iterator.hasNext();) {
                    String mealName = (String) iterator.next();
                    List<Food> foods = menu.getMeal((mealName));
                    for (int i = 0; i < foods.size(); i++) {
                        Food food = foods.get(i);
                        List<String> allergens = food.getAllergens();
                        ret.append("--"+food.getName());
                        ret.append(System.getProperty("line.separator"));
                        ret.append("    Station: "+food.getStation());
                        ret.append(System.getProperty("line.separator"));
                        for(String allergen: allergens){

                            ret.append("        Contains: "+allergen);
                            ret.append(System.getProperty("line.separator"));
                        }
                        ret.append(System.getProperty("line.separator"));
                    }
                }

        } catch (RuntimeException ex) {
            System.out.println("Dining court menu not available");
        }
        request.setAttribute("Menu",ret);
        RequestDispatcher view = request.getRequestDispatcher("menu.jsp");
//        view.forward(request, response);
        System.out.println(ret);
    }

}
