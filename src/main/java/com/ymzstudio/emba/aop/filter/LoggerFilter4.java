package com.ymzstudio.emba.aop.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoggerFilter4 extends OncePerRequestFilter {

    private final Logger logger = LoggerFactory.getLogger(LoggerFilter4.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
        logger.debug("we reached on 4!");
        filterChain.doFilter(request, response);
    }

}
