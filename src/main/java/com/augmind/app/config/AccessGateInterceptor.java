package com.augmind.app.config;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.augmind.app.web.AccessController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class AccessGateInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }

        HttpSession session = request.getSession(false);
        boolean granted = false;
        if (session != null) {
            Object val = session.getAttribute(AccessController.SESSION_ACCESS_GRANTED);
            granted = val instanceof Boolean b && b;
        }

        if (granted) {
            return true;
        }

        String uri = request.getRequestURI();
        if (uri.startsWith("/tasks") || uri.startsWith("/subjects") || uri.startsWith("/schedule") || uri.startsWith("/notes") || uri.startsWith("/stats")) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access denied");
            return false;
        }

        response.sendRedirect("/denied?reason=unauthorized");
        return false;
    }
}
