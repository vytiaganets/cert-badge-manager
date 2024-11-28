package com.example.solana.certbadgemanager.config;

import com.example.solana.certbadgemanager.util.JwtAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

   // private final JwtAuthFilter jwtAuthFilter;

    //public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
//        this.jwtAuthFilter = jwtAuthFilter;
//    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers(
//                        "/",
//                        "/api/auth/**",
//                        "/css/**",
//                        "/js/**",
//                        "/actuator/**",
//                        "/register",
//                        "/actuator",
//                        "/login",
//                                "/static/**",
//                                        "/v3/api-docs/**", // OpenAPI JSON
//                                        "/swagger-ui/**",  // Swagger ресурси
//                                        "/swagger-ui.html" // Swagger HTML
//                        ).permitAll()
//                        .anyRequest().authenticated()
//                )
//                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
//        return http.build();
//    }
//@Bean
//public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//    http
//            .csrf(csrf -> csrf.disable()) // Disable CSRF for testing
//            .authorizeHttpRequests(auth -> auth
//                    .requestMatchers(
//                            "/static/**",
//                            "/v3/api-docs/**", // OpenAPI JSON
//                            "/swagger-ui/**",  // Swagger ресурси
//                            "/swagger-ui.html" // Swagger HTML
//                                     ).permitAll() // Allow access to static files
//                    .requestMatchers("/**").permitAll() // Allow all other requests
//            )
//            .httpBasic(); // Use basic authentication
//
//    return http.build();
//}
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
            .csrf(csrf -> csrf.disable()) // Вимкнути CSRF
            .authorizeHttpRequests(auth -> auth
                    .anyRequest().permitAll() // Дозволити доступ до всіх ресурсів
            );

    return http.build();
}


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
