package com.scm.scm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.scm.scm.services.impl.SecuirityCustomUserDetailService;

@Configuration
public class SecurityConfig {

    @Autowired
    private SecuirityCustomUserDetailService userDetailService;

    @Autowired
    private OAuthSuccessHandler authSuccessHandler;

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeHttpRequests(authorizeHttpRequests -> {
            authorizeHttpRequests.requestMatchers("/user/**").authenticated();
            authorizeHttpRequests.anyRequest().permitAll();

        });

        httpSecurity.csrf(AbstractHttpConfigurer :: disable);
        httpSecurity.formLogin(formLogin -> {
            formLogin.loginPage("/login")
            .loginProcessingUrl("/authenticate")
            .successForwardUrl("/user/profile")
            .usernameParameter("email")
            .passwordParameter("password");
        });

        httpSecurity.logout(formLogout ->{

            formLogout.logoutUrl("/do-logout")
            .logoutSuccessUrl("/login?logout=true");
        });

        httpSecurity.oauth2Login(oauth ->{
            oauth.loginPage("/login")
            .successHandler(authSuccessHandler);
        });
        return httpSecurity.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
