package org.pronet.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final CustomUserDetailsService customUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)
            throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        (authorize) -> authorize
                                .requestMatchers("/", "/auth/register", "/auth/login")
                                .permitAll()
                ).authorizeHttpRequests(
                        (authorize) -> authorize
                                .requestMatchers(
                                        "/authors/add", "/authors/{authorId}/edit", "/authors/{authorId}/remove",
                                        "/books/{authorId}/add", "/books/{bookId}/edit", "/books/{bookId}/remove")
                                .hasAuthority("Admin"))
                .authorizeHttpRequests(
                        (authorize) -> authorize
                                .requestMatchers(
                                        "/authors","/authors/details/{authorId}", "/authors/search",
                                        "/books","/books/details/{bookId}", "/books/search")
                                .hasAnyAuthority("Admin", "User"))
                .formLogin(
                        form -> form
                                .loginPage("/auth/login")
                                .defaultSuccessUrl("/")
                                .loginProcessingUrl("/auth/login")
                                .failureUrl("/auth/login?error=true")
                                .permitAll()
                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout"))
                                .permitAll()
                );
        return http.build();
    }

    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder)
            throws Exception {
        authenticationManagerBuilder
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}