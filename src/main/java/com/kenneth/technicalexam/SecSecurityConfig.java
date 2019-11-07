package com.kenneth.technicalexam;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user")
                    .password(passwordEncoder().encode("user"))
                    .roles("USER")
                .and()
                .withUser("admin")
                    .password(passwordEncoder().encode("admin"))
                    .roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/employees").hasAnyRole("USER", "ADMIN")
                .antMatchers("/employee/new").hasRole("ADMIN")
                .antMatchers("/employee/save").hasRole("ADMIN")
                .antMatchers("/employee/delete/**").hasRole("ADMIN")
                .antMatchers("/api/**").hasRole("ADMIN")
                .antMatchers("/employee/{\\d+}").hasAnyRole("ADMIN", "USER")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();

//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/admin/**").hasRole("ADMIN")
//                .antMatchers("/anonymous*").anonymous()
//                .antMatchers("/login*").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login.html")
//                .loginProcessingUrl("/perform_login")
//                .defaultSuccessUrl("/homepage.html", true)
//                //.failureUrl("/login.html?error=true")
//                .failureHandler(authenticationFailureHandler())
//                .and()
//                .logout()
//                .logoutUrl("/perform_logout")
//                .deleteCookies("JSESSIONID")
//                .logoutSuccessHandler(logoutSuccessHandler());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
