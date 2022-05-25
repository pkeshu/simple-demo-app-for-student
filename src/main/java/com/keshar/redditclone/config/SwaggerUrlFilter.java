//package com.keshar.redditclone.config;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Configuration
//public class SwaggerUrlFilter extends OncePerRequestFilter {
//    private String servletPath;
//
//    private static final Logger LOG = LoggerFactory.getLogger(SwaggerUrlFilter.class);
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//        servletPath = request.getServletPath();
//        LOG.info("Servlet path {}", servletPath);
//        if (servletPath == null) {
//            servletPath = "";
//        }
//        if (request.getRequestURI().equals(servletPath + "/swagger-ui.html")) {
//            LOG.info("Request to '/swagger-ui.html' is redirected to '/error' path.");
//            response.sendRedirect(servletPath + "/error");
//        }
//        filterChain.doFilter(request, response);
//    }
//}
//
