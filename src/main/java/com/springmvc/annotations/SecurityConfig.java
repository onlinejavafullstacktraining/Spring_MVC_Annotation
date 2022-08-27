package com.springmvc.annotations;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Import(SpringMvcConfig.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//http.authorizeRequests().anyRequest().authenticated().and().formLogin();
		http
		 .authorizeRequests()
		 .antMatchers("/**")
		 .hasRole("USER")
		 .antMatchers("/admin/**").hasRole("ADMIN")
		 .antMatchers("/accessdenied").permitAll()
		 .antMatchers("/logout").permitAll()
		 .and().formLogin().failureUrl("/accessdenied")
		 .and().logout().logoutSuccessUrl("/logout")
		 .and().csrf().disable();
;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("spring").password("{noop}security").authorities("ROLE_USER");
	}
	

}
