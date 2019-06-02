package com.final_project.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    CustomAuthSuccessHandler customAuthSuccessHandler;

    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery(
                        "select email,password, active from users where email=?")
                .authoritiesByUsernameQuery(
                        "select email, role from users where email=?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String[] staticResources  =  {
                "/css/**",
                "/img/**",
                "/js/**"
        };

        String[] noAuthLinks = {
                "/",
                "/all-movies",
                "/all-theaters",
                "/movie/**",
                "/booking/**",
                "/all-movie-plays",
        };

        http.csrf().disable();

        http
                .authorizeRequests()
                .antMatchers(noAuthLinks).permitAll()
                .antMatchers(staticResources).permitAll()
                .antMatchers("/manage/**").access("hasRole('STAFF')")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").successHandler(customAuthSuccessHandler)
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }
}
