package liftoff.atlas.getcultured;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import liftoff.atlas.getcultured.controllers.AuthenticationController;
import liftoff.atlas.getcultured.models.User;
import liftoff.atlas.getcultured.models.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AuthenticationFilter implements HandlerInterceptor {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationController authenticationController;

    private static final List<String> publicWhitelist = Arrays.asList(
            "/",
            "/user/sign-up",
            "/user/login",
            "/user/forgot-password",
            "/script.js",
            "/styles.css",
            "/user-styles.css"
    );

    private static final List<String> whitelistUserWildcards = Arrays.asList(
            "/user/verifyEmail/",
            "/user/password-reset/" // handles authorizing password reset tby token
    );

    private static boolean isWhitelisted(String path) {
        for (String pathRoot : publicWhitelist) {
            if (path.equals(pathRoot)) {
                return true;
            }
        }

        for (String pathRoot : whitelistUserWildcards) {
            if (path.startsWith(pathRoot)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        if(isWhitelisted((request.getRequestURI()))) {
            return true;
        }

        HttpSession session = request.getSession();
        User authenticatedUser = authenticationController.getUserFromSession(session);

        // If a User is returned from the session (not null), the session is authenticated and the URI request is allowed
        if (authenticatedUser != null) {
            return true;
        }

        response.sendRedirect("/user/login");
        return false;
    }
}