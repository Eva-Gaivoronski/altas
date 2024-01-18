package liftoff.atlas.getcultured;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import liftoff.atlas.getcultured.controllers.AuthenticationController;
import liftoff.atlas.getcultured.models.User;
import liftoff.atlas.getcultured.models.data.UserRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AuthenticationFilter implements HandlerInterceptor {

    @Autowired
    AuthenticationController authenticationController;

    @Autowired
    UserRepository userRepository;

    private static final List<String> publicWhitelist = Arrays.asList(
            "/",
            "/unauthorized",
            "/user/sign-up",
            "/user/login",
            "/user/forgot-password",
            "/user/reset-password",
            "/script.js",
            "/styles.css",
            "/user-styles.css"
    );

    // Whitelists URIs related to /user/*/*, primarily for SecureToken values passed into URIs
    private static final List<String> whitelistWildcardUserURIs = Arrays.asList(
            "/user/verifyEmail/",
            "/user/password-reset/" // handles authorizing password reset tby token
    );

    private static boolean isWhitelisted(String path) {

        // Checks if path is pat of the explicitly whitelisted URIs
        for (String pathRoot : publicWhitelist) {
            if (path.equals(pathRoot)) {
                return true;
            }
        }

        // Checks if path starts with explicitly whitelisted wildcard URIs
        for (String pathRoot : whitelistWildcardUserURIs) {
            if (path.startsWith(pathRoot)) {
                return true;
            }
        }

        return false;
    }

    // All admin access will begin with this path
    private static boolean isAdminOnly(String path) {
        return path.startsWith("/admin");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        String requestURI = request.getRequestURI();

        // If the URI path passed into isWhitelisted returns true, return true on the preHandle
        if(isWhitelisted(requestURI)) {
            return true;
        }

        HttpSession session = request.getSession();
        User authenticatedUser = authenticationController.getUserFromSession(session);


        if(isAdminOnly(requestURI)) {
            if (authenticatedUser != null && authenticatedUser.getUserGroups().toString().contains("admin")) {
                return true;
            }

            // No 'admin' UserGroup found in User's membership Set, redirect to unauthorized page
            response.sendRedirect("/unauthorized");
            return false;
        }

        // TODO: Replace with more restrictive authorization; just because a user is authenticated doesn't mean they should have full run of a site; plus this will result in less mapping errors
        // If a User is returned from the session (not null), the session is authenticated and the URI request is allowed
        if (authenticatedUser != null) {
            return true;
        }

        response.sendRedirect("/user/login");
        return false;
    }
}