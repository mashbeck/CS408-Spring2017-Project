package boilerhungry.backend.Servlets;

import boilerhungry.backend.DiningCourt;
import boilerhungry.backend.DiningCourtAPI;
import boilerhungry.backend.purdue.PurdueDiningCourtAPI;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.List;


public class MenuServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String input;
        try{
             input = request.getParameter("viewMenu");
        }
        catch(NullPointerException e){
            System.out.println("requested parameter is null");
            return;
        }
        DiningCourt earhart = (DiningCourt) getServletContext().getAttribute("Earhart");
        out.println(input);
        out.flush();
    }

}
