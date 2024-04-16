package com.battleship201;

import com.battleship201.controller.RegistrationServlet;
import javax.servlet.http.HttpServlet;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import java.io.File;
// import java.util.logging.ConsoleHandler;
// import java.util.logging.Level;
// import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        // Logger tomcatLogger = Logger.getLogger("org.apache");
        // ConsoleHandler consoleHandler = new ConsoleHandler();
        // consoleHandler.setLevel(Level.ALL);  // Capture all levels of log
        // tomcatLogger.addHandler(consoleHandler);
        // tomcatLogger.setLevel(Level.ALL);  // Set logger to capture all levels

        // // Other loggers you might want to check
        // Logger.getLogger("org.apache.catalina.core").setLevel(Level.ALL);

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);

        String contextPath = "";
        String docBase = new File("webapp").getAbsolutePath();
        Context context = tomcat.addWebapp(contextPath, docBase);

        // Add servlet and map it
        String servletName = "RegistrationServlet";
        HttpServlet servlet = new RegistrationServlet();
        Tomcat.addServlet(context, servletName, servlet);
        context.addServletMappingDecoded("/register", servletName);

        try {
            tomcat.start();
            tomcat.getServer().await();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }
    }
}


