package com.test.eshopweb;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                // POZOR! Na poradi antMatchers ZALEZI!!!
                .antMatchers("/admin/**", "/actuator/**")
                .hasRole("ADMIN")
                .antMatchers("/**")
                .hasRole("USER")
                .and()
                .formLogin()
                .and()
                .httpBasic()
                .and()
                .logout();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.builder()
                        .username("jirka")
//                        .password("{noop}jirka")
                        .password("{bcrypt}$2a$10$D.qk3zAp8CmDbWD8/Rbwz.wHgVEjfvKcUo7pjqHFxILE76nZ0O3OK") // jirka
                        .roles("USER")
                        .build();
        return new InMemoryUserDetailsManager(user);
    }

}