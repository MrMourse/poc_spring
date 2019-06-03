package com.example.demo.components;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
// Autorise le CROSS DOMAIN.
public class CrossDomainFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain)
            throws ServletException, IOException {
        // Toutes les URI sont autorisées.
        httpServletResponse.addHeader("Access-Control-Allow-Origin", "*");
        // Les requêtes CRUD sont autorisées.
        httpServletResponse.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        // Les requêtes avec header sont autorisées.
        httpServletResponse.addHeader("Access-Control-Allow-Headers", "origin, content-type, accept, x-req");
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}