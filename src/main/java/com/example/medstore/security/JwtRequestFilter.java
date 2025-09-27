package com.example.medstore.security;
 
import java.io.IOException;
 

 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
 
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
 
 
@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	private static final Logger logger = LoggerFactory.getLogger(JwtRequestFilter.class);
 
	@Autowired
	private MyUserDetailsService userDetailsService;
 
	@Autowired
	private JwtUtil jwtUtil;
 
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		try {			
			final String authorizationHeader = request.getHeader("Authorization");
			String username = null;
			String jwt = null;
			if (! request.getRequestURL().toString().contains("/login") || request.getMethod() != "OPTIONS") {
				if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
					jwt = authorizationHeader.substring(7);
					username = jwtUtil.extractUsername(jwt);
//					System.out.println("username: "+jwt);
				}
//				System.out.println("12");
				if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
 
					UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
 
					if (jwtUtil.validateToken(jwt, userDetails)) {
 
						UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
								userDetails, null, userDetails.getAuthorities());
						usernamePasswordAuthenticationToken
								.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
						SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
					}
				}
				chain.doFilter(request, response);
			}else {
				chain.doFilter(request, response);
			}
		} catch (IOException e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}
	}
}