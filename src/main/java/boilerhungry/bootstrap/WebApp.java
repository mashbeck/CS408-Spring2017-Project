package boilerhungry.bootstrap;

import boilerhungry.servlets.DietaryPreferencesServlet;
import boilerhungry.servlets.HomeServlet;
import boilerhungry.servlets.MenuServlet;
import boilerhungry.servlets.MyFoodsServlet;
import org.apache.tomcat.InstanceManager;
import org.apache.tomcat.SimpleInstanceManager;
import org.eclipse.jetty.jsp.JettyJspServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.log.JavaUtilLog;
import org.eclipse.jetty.util.log.Log;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

public class WebApp {

    private int port;
    private Server server;

    public WebApp(int port) {
        this.port = port;
        LoggingUtil.config();
        Log.setLog(new JavaUtilLog());
    }

    public void start() throws Exception {
        if (server != null) {
            throw new RuntimeException("The server has already been started");
        }
        server = new Server(port);
        server.setHandler(getServletContextHandler());
        server.start();
    }

    public void stop() throws Exception {
        if (server != null) {
            server.stop();
        }
    }

    private File getScratchDir() throws IOException {
        File tempDir = new File(System.getProperty("java.io.tmpdir"));
        File scratchDir = new File(tempDir.toString(), "embedded-jetty-jsp");
        if (!scratchDir.exists()) {
            if (!scratchDir.mkdirs()) {
                throw new IOException("Unable to create scratch directory: " + scratchDir);
            }
        }
        return scratchDir;
    }

    private ServletContextHandler getServletContextHandler() throws Exception {
        ClassLoader jspClassLoader = new URLClassLoader(new URL[0], this.getClass().getClassLoader());
        String resourceBase = this.getClass().getResource("/webroot/").toURI().toASCIIString();
        ServletContextHandler ctx = new ServletContextHandler(ServletContextHandler.SESSIONS);
        ctx.setContextPath("/");
        ctx.setAttribute("javax.servlet.context.tempdir", getScratchDir());
        ctx.setResourceBase(resourceBase);
        ctx.setAttribute(InstanceManager.class.getName(), new SimpleInstanceManager());
        // Process Jsp Files
        ctx.addBean(new JspStarter(ctx));
        ctx.setClassLoader(jspClassLoader);
        ctx.addServlet(new ServletHolder("jsp", JettyJspServlet.class), "*.jsp");
        // Application servlets
        ServletHolder homeServlet = ctx.addServlet(HomeServlet.class, "/home");
        homeServlet.setInitOrder(1);
        ctx.addServlet(MenuServlet.class, "/menu/*");
        ctx.addServlet(MyFoodsServlet.class,"/myfoods");
        ctx.addServlet(DietaryPreferencesServlet.class, "/preferences");
        // Default Servlet
        ServletHolder defaultServlet = new ServletHolder("default", DefaultServlet.class);
        defaultServlet.setInitParameter("resourceBase", resourceBase);
        defaultServlet.setInitParameter("dirAllowed", "false");
        ctx.addServlet(defaultServlet, "/");
        return ctx;
    }

}
