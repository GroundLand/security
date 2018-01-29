package com.cl.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * Created by evel on 2018/1/22.
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/resources/**", "/signup", "/static/**").permitAll()
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login").permitAll()
                .and()
                    .exceptionHandling().accessDeniedPage("/403");
        http.logout().logoutSuccessUrl("/");
    }

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager(); // 在内存中存放用户信息
        //User对象为UserDetail
        manager.createUser(User.withUsername("user").password("password").roles("USER").build());
        manager.createUser(User.withUsername("admin").password("password").roles("USER", "ADMIN").build());
        return manager;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }
}
