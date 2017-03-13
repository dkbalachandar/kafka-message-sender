package com;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.servlet.WebappContext;
import org.glassfish.jersey.servlet.ServletContainer;

import javax.servlet.ServletRegistration;

/**
 * Main class.
 *
 * Starts a Grizzly server and deploy rest application on it
 */
public class Main {

    public static void main(String[] args) throws Exception {

        HttpServer server = HttpServer.createSimpleServer("/", 8081);
        WebappContext ctx = new WebappContext("api");
        ServletRegistration jerseyServlet = ctx.addServlet("jersey", ServletContainer.class);
        jerseyServlet.addMapping("/api/*");
        jerseyServlet.setInitParameter("jersey.config.server.provider.packages", "com.resource");
        jerseyServlet.setInitParameter("com.sun.jersey.api.json.POJOMappingFeature", "true");
        ctx.deploy(server);
        try {
            server.start();
            Thread.currentThread().join();
        } finally {
            server.shutdownNow();
        }
    }
}

