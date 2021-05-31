package com.Grupo19OO22021.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.Grupo19OO22021.services.UsuarioService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("usuarioService")
	private UsuarioService usuarioService;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usuarioService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/include/**","/css/**","/icons/**","/img/**","/js/**","/layer/**","/","/rodado/new","/rodado/save","/home","/permiso/newPermisoPeriodo","/permiso/savePermisoPeriodo").permitAll()
				//HTTPS QUE PUEDE INGRESAR UN AUDITOR
			/*
				.antMatchers("/usuario/new").hasAnyRole("ADMIN")
				.antMatchers("/usuario/new/").hasAnyRole("ADMIN")
				.antMatchers("/usuario/save").hasAnyRole("ADMIN")
				.antMatchers("/usuario/home/**").hasAnyRole("ADMIN")
				.antMatchers("/usuario/editar/**").hasAnyRole("ADMIN")
			    .antMatchers("/usuario/eliminar/**").hasAnyRole("ADMIN")
			    
			    .antMatchers("/perfil/new").hasAnyRole("ADMIN")
			    .antMatchers("/perfil/new/").hasAnyRole("ADMIN")
				.antMatchers("/perfil/seve").hasAnyRole("ADMIN")
				.antMatchers("/perfil/home/**").hasAnyRole("ADMIN")
				.antMatchers("/perfil/editar/**").hasAnyRole("ADMIN")
			    .antMatchers("/perfil/eliminar/**").hasAnyRole("ADMIN")
				
		*/		
				.anyRequest().authenticated()
			.and()
				.formLogin().loginPage("/usuario/login").loginProcessingUrl("/usuario/loginprocess")
				.usernameParameter("nombreUsuario").passwordParameter("password")
				.defaultSuccessUrl("/usuario/loginsuccess").permitAll()
			.and()
				.logout().logoutUrl("/usuario/logout").logoutSuccessUrl("/usuario/logout").permitAll();
	}
	
	 @Bean
	    public BCryptPasswordEncoder passwordEncoder() {
		 BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
	        return bCryptPasswordEncoder;
	    }

	
	 
	 
	 
	 
}

