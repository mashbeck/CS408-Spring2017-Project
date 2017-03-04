//
//  ========================================================================
//  Copyright (c) 1995-2016 Mort Bay Consulting Pty. Ltd.
//  ------------------------------------------------------------------------
//  All rights reserved. This program and the accompanying materials
//  are made available under the terms of the Eclipse Public License v1.0
//  and Apache License v2.0 which accompanies this distribution.
//
//      The Eclipse Public License is available at
//      http://www.eclipse.org/legal/epl-v10.html
//
//      The Apache License v2.0 is available at
//      http://www.opensource.org/licenses/apache2.0.php
//
//  You may elect to redistribute this code under either of these licenses.
//  ========================================================================
//

package boilerhungry.bootstrap;

import boilerhungry.servlets.HomeServlet;
import boilerhungry.servlets.MenuServlet;
import org.apache.tomcat.InstanceManager;
import org.apache.tomcat.SimpleInstanceManager;
import org.eclipse.jetty.jsp.JettyJspServlet;
import org.eclipse.jetty.server.Server;
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
        ServletContextHandler sch = new ServletContextHandler(ServletContextHandler.SESSIONS);
        sch.setContextPath("/");
        sch.setAttribute("javax.servlet.context.tempdir", getScratchDir());
        sch.setResourceBase(this.getClass().getResource("/webroot").toURI().toASCIIString());
        sch.setAttribute(InstanceManager.class.getName(), new SimpleInstanceManager());
        sch.addBean(new JspStarter(sch));
        sch.setClassLoader(new URLClassLoader(new URL[0], this.getClass().getClassLoader()));
        sch.addServlet(new ServletHolder("jsp", JettyJspServlet.class), "*.jsp");
        sch.addServlet(MenuServlet.class, "/menu/*");
        sch.addServlet(HomeServlet.class, "/").setInitOrder(1);
        return sch;
    }

}
