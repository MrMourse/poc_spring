package com.example.demo.components;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/filter/OncePerRequestFilter.html
 */
@Component
public class CrossDomainFilter extends OncePerRequestFilter {

    /**
     * This doFilter implementation stores a request attribute for "already filtered",
     * proceeding without filtering again if the attribute is already there.
     * @param httpServletRequest, the ServletRequest object contains the client's request.
     * @param httpServletResponse, the ServletResponse object contains the filter's response
     * @param filterChain, the FilterChain for invoking the next filter or the resource
     * @throws ServletException, if an exception occurs that interferes with the filter's normal operation
     * @throws IOException, if an I/O related error has occurred during the processing
     */
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        // Toutes les URI sont autorisées.
        httpServletResponse.addHeader("Access-Control-Allow-Origin",
                                    "*");
        // Les requêtes CRUD sont autorisées.
        httpServletResponse.addHeader("Access-Control-Allow-Methods",
                                    "GET, POST, PUT, DELETE, OPTIONS");
        // Les requêtes avec header sont autorisées.
        httpServletResponse.addHeader("Access-Control-Allow-Headers",
                                    "origin, content-type, accept, x-req");
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}