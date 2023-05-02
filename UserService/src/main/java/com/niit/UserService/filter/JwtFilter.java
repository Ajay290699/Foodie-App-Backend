package com.niit.UserService.filter;

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
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
//        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
//        String authHeader = httpServletRequest.getHeader("authorization");
//
//        try {
//            if ("OPTIONS".equals(httpServletRequest.getMethod())) {
//                httpServletResponse.setStatus(HttpServletResponse.SC_OK);
//                filterChain.doFilter(httpServletRequest, httpServletResponse);
//            } else if (authHeader == null || authHeader.startsWith("Bearer") || authHeader.contains("null")) {
//                throw new ServletException("Missing or Invalid Exception");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ServletException e) {
//            e.printStackTrace();
//        }
//
//        String token = authHeader.substring(7);
//        Claims claims = Jwts.parser().setSigningKey("securityKey").parseClaimsJws(token).getBody();
//
//        httpServletRequest.setAttribute("claims", claims);
//        filterChain.doFilter(httpServletRequest, httpServletResponse);
//    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer")) {
            throw new ServletException("Token missing");
        } else {

            String token = authHeader.substring(7);
            Claims claims = Jwts.parser().setSigningKey("mysecretkey").parseClaimsJws(token).getBody();

            System.out.println("claims: " + claims);
            request.setAttribute("emailId", claims.get("user_email"));
            filterChain.doFilter(request, response);
        }
    }

//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        String authHeader = request.getHeader("Authorization");
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            response.getWriter().write("Token missing or invalid");
//            return;
//        }
//        String token = authHeader.substring(7);
//        try {
//            String secretKey = System.getenv("securityKey");
//            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
//                    .parseClaimsJws(token);
//            request.setAttribute("emailId", claims.getBody().get("user_email").toString());
//            filterChain.doFilter(request, response);
//        } catch (JwtException e) {
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            response.getWriter().write("Token invalid: " + e.getMessage());
//        }
//    }

}