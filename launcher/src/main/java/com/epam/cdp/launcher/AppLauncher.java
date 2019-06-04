package com.epam.cdp.launcher;

public class AppLauncher {

    public static void main(String[] args) throws Exception {

        //The port that we should run on can be set into an environment variable
        //Look for that variable and default to 8080 if it isn't there.
        String webPort = System.getenv("PORT");
        if (webPort == null || webPort.isEmpty()) {
            webPort = "8080";
        }

        EmbeddedTomcat tomcat = new EmbeddedTomcat(Integer.valueOf(webPort));
        tomcat.start();

        Runtime.getRuntime().addShutdownHook(new Thread(tomcat::stop));
    }

}
