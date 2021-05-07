package com.itacademy.tests.utils;


import com.softserveinc.ita.homeproject.blog.ApiClient;
import com.softserveinc.ita.homeproject.blog.ServerConfiguration;
import org.glassfish.jersey.logging.LoggingFeature;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class ApiClientUtil {
    private static final String APPLICATION_EXTERNAL_PORT = System.getProperty("blog.application.external.port", "8080");
    private static final String APPLICATION_ADMIN_EMAIL = System.getProperty("blog.application.admin.username", "test@admin.com");
    private static final String APPLICATION_ADMIN_PASSWORD = System.getProperty("blog.application.admin.password", "passworD321");
    private static final String VERBOSE_LOGGING = System.getProperty("verbose.tests.logging", "true");

    public static ApiClient getClient(String email, String password) {
        ApiClient client = new ApiClient();
        setLoggingFeature(client);
        setServers(client);
        client.setUsername(email);
        client.setPassword(password);
        return client;
    }

    public static ApiClient getClient() {
        ApiClient client = new ApiClient();
        setLoggingFeature(client);
        setServers(client);
        client.setUsername(APPLICATION_ADMIN_EMAIL);
        client.setPassword(APPLICATION_ADMIN_PASSWORD);
        return client;
    }

    public static ApiClient getUnauthorizedClient() {
        ApiClient client = new ApiClient();
        setLoggingFeature(client);
        setServers(client);
        return client;
    }

    private static void setLoggingFeature(ApiClient client) {
        if (Boolean.parseBoolean(VERBOSE_LOGGING)) {
            Logger logger = Logger.getLogger(ApiClient.class.getName());
            client.getHttpClient()
               .register(new LoggingFeature(logger, Level.INFO, LoggingFeature.Verbosity.PAYLOAD_ANY, 8192));
        }
    }

    private static void setServers(ApiClient client) {
        client.setServers(List.of(new ServerConfiguration("http://localhost:" +
                APPLICATION_EXTERNAL_PORT + "/api/1", "No description provided", new HashMap<>())));
    }

}
