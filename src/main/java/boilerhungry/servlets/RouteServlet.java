package boilerhungry.servlets;

import javax.servlet.http.HttpServlet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class RouteServlet extends HttpServlet {

    private static final Pattern routePattern = Pattern.compile("/?([^/]+)/?");

    protected List<String> getRoutes(String pathInfo) {
        List<String> routes = new ArrayList<>();
        if (pathInfo != null) {
            Matcher matcher = routePattern.matcher(pathInfo);
            while (matcher.find()) {
                routes.add(matcher.group(1));
            }
        }
        return routes;
    }

}
