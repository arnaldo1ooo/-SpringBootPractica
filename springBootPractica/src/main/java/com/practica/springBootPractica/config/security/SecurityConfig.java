package com.practica.springBootPractica.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private AutenticacionService autenticacionService;
	
	
	// Configuraciones de autenticacion
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(autenticacionService)
			.passwordEncoder(new BCryptPasswordEncoder());
	}
	
	/*public static void main(String[] args) {
		System.out.println(new BCryptPasswordEncoder().encode("123456")); //Para encriptar en bcrypt
	}*/
	

	// Configuraciones de autorizacion de acceso
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers(HttpMethod.GET, "/topicos").permitAll() // Pone como publicos todas las consultas a topicos
			.antMatchers(HttpMethod.GET, "/topicos/*").permitAll() // Pone como publicos todas las consultas individuales a topicos
			.anyRequest().authenticated()
			.and().formLogin();
	}
	
	// Configuraciones de recursos estaticos (js, css, png, etc)
	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
	}
}
