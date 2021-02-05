package com.test.eshopweb;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
                .logout()
                .and()
                .rememberMe();
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

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("jirka"));
        System.out.println(encoder.encode("jirka"));
        System.out.println(encoder.encode("jirka"));
        System.out.println(encoder.encode("jirka"));
        System.out.println(encoder.encode("jirka"));
        System.out.println(encoder.matches("jirka", "$2a$10$y8EIT12eqssQDPJEuHmik.Oezve4210VYNBMQnWJ8VojE0akRtqOi"));
    }

}