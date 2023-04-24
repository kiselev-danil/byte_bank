package com.example.byte_bank.config;


import com.example.byte_bank.service.UserService;
import com.example.byte_bank.service.userDetails.MyUserDetails;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private DataSource dataSource;
    private UserDetailsService userDetailsService;

    public SecurityConfig(DataSource dataSource, UserService userService) {
        this.dataSource = dataSource;
        this.userDetailsService = userService;
    }

//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests()
////                .requestMatchers("/users/getById").authenticated()
////                .requestMatchers("/users/getByUsername").authenticated()
//                .anyRequest().permitAll()
////                .and().formLogin().usernameParameter("login")
////                .defaultSuccessUrl("/users")
////                .permitAll()
////                .and().logout().logoutSuccessUrl("/").permitAll();
////                .anyRequest().permitAll()
//        ;
//    }

//    @Bean
//    public UserDetailsService userDetailsService() {
////        UserDetails userDetails = User.withDefaultPasswordEncoder()
////                .username("test")
////                .password("1234567890")
////                .roles("USER").build();
//        return new InMemoryUserDetailsManager(userDetails);
//    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    private UserDetailsService userDetailsService() {
        return userDetailsService;
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //anyRequest().permitAll()
//        httpSecurity.authorizeHttpRequests().requestMatchers("/users/register").anonymous()
////                .requestMatchers("/admin").hasRole("admin")
////                .requestMatchers("/users").fullyAuthenticated()
//                .anyRequest().permitAll()
//                .and().formLogin().usernameParameter("login").defaultSuccessUrl("/users").permitAll().and()
//                .csrf().disable()
//                .logout().logoutSuccessUrl("/").permitAll()

        http
                .csrf().disable();
////                .requestMatchers("/users/getById").authenticated()
////                .requestMatchers("/users/getByUsername").authenticated()
//                .anyRequest().permitAll()
//                .and().formLogin().usernameParameter("login")
//                .defaultSuccessUrl("/users")
//                .permitAll()
//                .and().logout().logoutSuccessUrl("/").permitAll();
//                .anyRequest().permitAll()
        

        return http.build();
    }
}
