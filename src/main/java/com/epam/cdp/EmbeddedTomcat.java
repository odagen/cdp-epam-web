package com.epam.cdp;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.apache.tomcat.util.http.fileupload.FileUtils;

import javax.servlet.ServletException;
import java.io.File;
import java.io.IOException;

class EmbeddedTomcat {

    private static final String BASE_DIR = ".tmp";
    private static final String WEB_APP_DIR = "src/main/webapp/";

    final Tomcat tomcat;

    EmbeddedTomcat(int port) {
        this.tomcat = configureTomcat(port);
    }

    void start() {
        try {
            deploy();
            tomcat.start();
            tomcat.getServer().await();
        } catch (ServletException | LifecycleException exc) {
            throw new RuntimeException(exc);
        }
    }

    void stop() {
        try {
            tomcat.stop();
            tomcat.destroy();
        } catch (LifecycleException exc) {
            throw new RuntimeException(exc);
        } finally {
            // Tomcat creates a work folder where the temporary files are stored
            try {
                FileUtils.deleteDirectory(new File(BASE_DIR));
            } catch (IOException exc) {
                exc.printStackTrace();
            }
        }
    }

    void deploy() throws ServletException {
        StandardContext ctx;
        ctx = (StandardContext) tomcat.addWebapp("/", new File(WEB_APP_DIR).getAbsolutePath());
        System.out.println("configuring app with basedir: " + new File("./" + WEB_APP_DIR).getAbsolutePath());

        // Declare an alternative location for your "WEB-INF/classes" dir
        // Servlet 3.0 annotation will work
        File additionWebInfClasses = new File("target/classes");
        WebResourceRoot resources = new StandardRoot(ctx);
        resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes",
                additionWebInfClasses.getAbsolutePath(), "/"));
        ctx.setResources(resources);
    }

    private Tomcat configureTomcat(int port) {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(port);
        tomcat.setBaseDir(BASE_DIR);
        tomcat.getHost().setAppBase(BASE_DIR);
        tomcat.getHost().setDeployOnStartup(true);
        tomcat.getHost().setAutoDeploy(true);

        return tomcat;
    }
}
