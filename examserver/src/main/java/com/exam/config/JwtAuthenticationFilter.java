package com.exam.config;

import com.exam.service.impl.UserDetailsServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private JwtUtils jwtUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

       final String requestTokenHarder = request.getHeader("Authorization");
        System.out.println(requestTokenHarder);
        String username = null;
        String jwtToken = null;

        if(requestTokenHarder != null && requestTokenHarder.startsWith("Bearer "))
        {
            jwtToken = requestTokenHarder.substring(7);
            try {
                username = this.jwtUtil.extractUsername(jwtToken);
            }catch (ExpiredJwtException e)
            {
                e.printStackTrace();
                System.out.println("jwt toked has expired");
            }catch (Exception e)
            {
                e.printStackTrace();
                System.out.println("error");
            }

        }else{
            System.out.println("invalid token");
        }

        //validate token
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null)
        {
           final UserDetails userDetails =  this.userDetailsServiceImpl.loadUserByUsername(username);
           if(this.jwtUtil.validateToken(jwtToken,userDetails)){
               UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
               //token valid
               usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
               SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
           }
        }else{
            System.out.println("token is not valid");
        }
       filterChain.doFilter(request,response);
    }
}
