package com.practica.springBootPractica.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.practica.springBootPractica.repository.UsuarioRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private AutenticacionService autenticacionService;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	public static void main(String[] args) {
		//System.out.println("Encriptado " + new BCryptPasswordEncoder().encode("123456")); //Para encriptar en bcrypt
	}
	
	
	// Configuraciones de autenticacion
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(autenticacionService)
			.passwordEncoder(new BCryptPasswordEncoder());
	}

	// Configuraciones de autorizacion de acceso
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers(HttpMethod.GET, "/topicos").permitAll() // Pone como publicos todas las consultas a topicos
			.antMatchers(HttpMethod.GET, "/topicos/*").permitAll() // Pone como publicos todas las consultas individuales a topicos
			.antMatchers(HttpMethod.POST, "/auth").permitAll() // Permite el acceso a autenticacion, se debe habilitar para que los usuarios puedan loguearse
			.antMatchers(HttpMethod.GET, "/actuator/**").permitAll() // Solo habilitar en ambiente de pruebasa
			.anyRequest().authenticated()
			.and().csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and().addFilterBefore(new AutenticacionPorTokenFilter(tokenService, usuarioRepository), UsernamePasswordAuthenticationFilter.class);	// Para que la auth de token se ejecute primero
	}
	
	// Configuraciones de recursos estaticos (js, css, png, etc)
	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
	}
}
