package com.niit.stackroute.restaurant.service.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String header = httpServletRequest.getHeader("authorization");

        if (header == null || !header.startsWith("Bearer")) {
            throw new ServletException("Invalid Token");
        } else {

            String ownerToken = header.substring(7);

            Claims claims = Jwts.parser()
                    .setSigningKey("Fooide-app-owner-key")
                    .parseClaimsJws(ownerToken)
                    .getBody();

            httpServletRequest.setAttribute("owner-emailId", claims.get("emailId"));
            filterChain.doFilter(servletRequest, httpServletResponse);
        }
    }
}
