package com.opytha.droprofitacademy.configuration;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.AbstractRequestMatcherRegistry;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(authorizeRequest ->
                authorizeRequest
                        .requestMatchers(HttpMethod.GET ,"/api/courses/All").permitAll()
                        .requestMatchers(HttpMethod.POST ,"/api/courses/create").permitAll()
                        .requestMatchers(HttpMethod.PATCH ,"/api/courses/delete").permitAll()
                        .anyRequest().permitAll()
        );

        http.csrf(AbstractHttpConfigurer::disable);

        http.headers(httpSecurityHeadersConfigurer -> httpSecurityHeadersConfigurer.frameOptions(
                HeadersConfigurer.FrameOptionsConfig::disable));

         http.formLogin(formLogin -> formLogin
                .loginPage("/login")
                .loginProcessingUrl("/api/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .successHandler((request, response, authentication) -> {clearAuthenticationAttributes(request);})
                .failureHandler((request, response, exception) -> response.sendError(401))
                .permitAll()
        );
       // http.formLogin(withDefaults());

        //http.exceptionHandling( exceptionHandlingConfigurer ->
          //      exceptionHandlingConfigurer.authenticationEntryPoint((request, response, authException) -> response.sendError(403)));

        http.logout(httpSecurityLogoutConfigurer ->
                httpSecurityLogoutConfigurer
                        .logoutUrl("/api/logout")//endpoint de logout para hacer un post
                        .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler())
                        .deleteCookies("JSESSIONID"));  // eliminacion de la cookie

        http.rememberMe(Customizer.withDefaults());


        return http.build();
    }

    private void clearAuthenticationAttributes(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (session != null) {

            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);

        }
    }
}
