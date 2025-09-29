package com.example.medstore.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
 
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
 
@Configuration
@EnableWebSecurity
public class WebConfig {
 
	@Autowired
	private UserDetailsService myUserDetailsService1;
 
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	@Autowired
	private JWTAuthenticationEntryPoint point;
 
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
 
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myUserDetailsService1).passwordEncoder(passwordEncoder());
	}
 
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().requestMatchers("/v2/api-docs", "/v3/api-docs", "/configuration/ui", "/swagger-resources/**",
				"/configuration/security", "/swagger-ui.html", "/webjars/**", "/rest/**");
	}
	
  
	@Bean
	protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {

	    httpSecurity
	        .csrf(csrf -> csrf.disable())
	        .cors() // ✅ Enable CORS (do NOT disable it)
	        .and()
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers("/login/**", "/forgot/**").permitAll()
	            .anyRequest().authenticated()
	        )
	        .exceptionHandling(ex -> ex.authenticationEntryPoint(point))
	        .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

	    httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

	    return httpSecurity.build();
	}

 
}