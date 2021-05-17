package com.itacademy.tests.utils;


import com.softserveinc.ita.homeproject.blog.ApiClient;
import com.softserveinc.ita.homeproject.blog.ApiException;
import com.softserveinc.ita.homeproject.blog.ApiResponse;
import com.softserveinc.ita.homeproject.blog.ServerConfiguration;
import org.glassfish.jersey.logging.LoggingFeature;

import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public final class ApiClientUtil {
    private static final String APPLICATION_EXTERNAL_PORT = System.getProperty("blog.application.external.port", "8080");
    private static final String APPLICATION_ADMIN_NAME = System.getProperty("blog.application.admin.username", "Tertey");
    private static final String APPLICATION_ADMIN_PASSWORD = System.getProperty("blog.application.admin.password", "passworD321");
    private static final String APPLICATION_MODERATOR_NAME = System.getProperty("blog.application.moderator.username", "Proselit");
    private static final String APPLICATION_MODERATOR_PASSWORD = System.getProperty("blog.application.moderator.password", "passworD321");
    private static final String APPLICATION_BLOGGER_NAME = System.getProperty("blog.application.blogger.username", "CheGevara");
    private static final String APPLICATION_BLOGGER_PASSWORD = System.getProperty("blog.application.blogger.password", "passworD321");
    private static final String VERBOSE_LOGGING = System.getProperty("verbose.tests.logging", "true");

    public static ApiClient getClient(String name, String password) {
        ApiClient client = new ApiClient();
        setLoggingFeature(client);
        setServers(client);
        client.setUsername(name);
        client.setPassword(password);
        return client;
    }

    public static ApiClient getAdminClient() {
        ApiClient client = new ApiClient();
        setLoggingFeature(client);
        setServers(client);
        client.setUsername(APPLICATION_ADMIN_NAME);
        client.setPassword(APPLICATION_ADMIN_PASSWORD);
        return client;
    }
    public static ApiClient getModeratorClient() {
        ApiClient client = new ApiClient();
        setLoggingFeature(client);
        setServers(client);
        client.setUsername(APPLICATION_MODERATOR_NAME);
        client.setPassword(APPLICATION_MODERATOR_PASSWORD);
        return client;
    }

    public static ApiClient getBloggerClient() {
        ApiClient client = new ApiClient();
        setLoggingFeature(client);
        setServers(client);
        client.setUsername(APPLICATION_BLOGGER_NAME);
        client.setPassword(APPLICATION_BLOGGER_PASSWORD);
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

    public static void checkAdminModerBlogger(boolean role, int statusCode){
        if (role) {
            assertNotEquals(Response.Status.UNAUTHORIZED.getStatusCode(), statusCode);
            assertNotEquals(Response.Status.FORBIDDEN.getStatusCode(), statusCode);
        } else {
            assertEquals(Response.Status.FORBIDDEN.getStatusCode(), statusCode);
        }
    }

    public static int setStatusCode(Function<ApiClient,?> action, ApiClient apiClient) {
        try {
            ApiResponse<?> resp = (ApiResponse<?>) action.apply(apiClient);
            return resp.getStatusCode();
        } catch (ApiException e) {
            return e.getCode();
        }

    }

}
