package boilerhungry;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * Created by eric on 2/5/17.
 */
public class WebApp {

    private static final int SERVER_PORT = 8080;
    private static final String CONTEXT_PATH = "/boilerhungry";
    private static final String WEBAPP_DIR = "src/main/webapp/";
    private static final String WEBXML = WEBAPP_DIR + "/WEB-INF/web.xml";

    public Server createServer() {
        Server server = new Server(SERVER_PORT);

        WebAppContext root = new WebAppContext();
        root.setContextPath(CONTEXT_PATH);
        root.setDescriptor(WEBXML);
        root.setResourceBase(WEBAPP_DIR);
        root.setParentLoaderPriority(true);
        server.setHandler(root);

        return server;
    }

}
