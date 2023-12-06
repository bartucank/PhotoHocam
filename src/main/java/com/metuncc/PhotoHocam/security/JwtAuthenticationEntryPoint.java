package com.metuncc.PhotoHocam.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  This class represents an Authentication Entry Point for JWT authentication.
 *  Actually AuthenticationEntryPoint interface implemented here.
 *  If there is un authorized attempt, our service return 401 http status with is UNAUTHORIZED.
 * @see org.springframework.security.web.AuthenticationEntryPoint
 * **/
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
    }

}