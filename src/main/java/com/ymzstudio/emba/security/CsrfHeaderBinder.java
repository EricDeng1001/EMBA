package com.ymzstudio.emba.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 把csrfFilter中设置的cookie放在header
 */
@Component
public final class CsrfHeaderBinder extends OncePerRequestFilter implements AuthenticationSuccessHandler,
                                                                            AuthenticationFailureHandler,
                                                                            LogoutSuccessHandler {

    private static final String HEADER = "X-CSRF-TOKEN";

    private static final Logger logger = LoggerFactory.getLogger(CsrfHeaderBinder.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) {
        logger.debug("bind handler called");
        bindHeader(request, response);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        logger.debug("bind handler called");
        bindHeader(request, response);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                Authentication authentication) {
        logger.debug("bind handler called");
        bindHeader(request, response);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
        logger.debug("bind filter called");
        bindHeader(request, response);
        filterChain.doFilter(request, response);
    }

    private static void bindHeader(HttpServletRequest request, HttpServletResponse response) {
        CsrfToken token = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        if (token != null) {
            response.setHeader(HEADER, token.getToken());
        }
    }

}
