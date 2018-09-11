package com.huntingcube.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

@Configuration
@EnableWebSecurity
public class SpringRestSecurity extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure (AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("javaInUse").password("javaInUse").roles("USER");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/**").authorizeRequests().anyRequest().hasRole("USER")
                .and().formLogin().loginPage("/login.jsp")
                .failureUrl("/login.jsp?error=1").loginProcessingUrl("/login")
                .permitAll().and().logout()
                .logoutSuccessUrl("/listEmployees.html");

    }

}
