package com.example.LibrarySystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.LibrarySystem.repository.StudentRepository;


import lombok.RequiredArgsConstructor;


@Configuration
@RequiredArgsConstructor
public class AppConfig {

  private final StudentRepository repo;
	//private final TeacherRepository repository;
	@Bean
	//@Primary
	public UserDetailsService userDetailsService()
	{
		return username->repo.findByEmail(username)
				.orElseThrow(()->new UsernameNotFoundException("User not Found"));
	}
	
	/*@Bean
	public UserDetailsService userDetailsService1()
	{
		return username->repository.findByEmail(username)
				.orElseThrow(()->new UsernameNotFoundException("User not Found"));
	}*/
	@Bean
	//@Primary
	public AuthenticationProvider authenticationProvider()
	{
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}
	

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	 @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
	        return config.getAuthenticationManager();
	    }
}
