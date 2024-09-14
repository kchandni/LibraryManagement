package com.example.LibrarySystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.LibrarySystem.model.UserRole;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	 private final JWTAuthenticationFilter jwtAuthFilter;
		private final AuthenticationProvider AuthenticationProvider;
		
	
	@Bean
	
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	
		http.csrf(csrf->csrf.disable())
		 .authorizeHttpRequests(auth->auth.requestMatchers("/api/library/admin/**").permitAll()
				 .requestMatchers("api/library/student/register").permitAll()
				 .requestMatchers("api/library/student/login").permitAll()
				 .requestMatchers("api/library/student/**").hasAnyAuthority(UserRole.STUDENT.name())
		         // .requestMatchers("api/library/tea/**").hasAnyAuthority(UserRole.TEACHER.name())
				 .anyRequest()
				 .authenticated())
				 .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				 .authenticationProvider(AuthenticationProvider)
				 .addFilterBefore(jwtAuthFilter,UsernamePasswordAuthenticationFilter.class);
				 return http.build();
	}
	
}
