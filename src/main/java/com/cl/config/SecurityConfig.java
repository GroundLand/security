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
                    .antMatchers("/css/**", "/js/**", "/fonts/**", "/index").permitAll()
                    .antMatchers("/users/**").hasRole("USER")
                    .antMatchers("/admins/**").hasRole("ADMIN")
                    .and()
                .formLogin()
                    .loginPage("/login").failureForwardUrl("login-error")
                    .and()
                .exceptionHandling().accessDeniedPage("/403");
        http.logout().logoutSuccessUrl("/");
    }

    @Bean
    public UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager(); // 在内存中存放用户信息
        //User对象为UserDetail
        manager.createUser(User.withUsername("waylau").password("123456").roles("USER").build());
        manager.createUser(User.withUsername("admin").password("123456").roles("USER","ADMIN").build());
        return manager;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }
}
