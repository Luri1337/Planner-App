package com.example.day_planner.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        User.UserBuilder users = User.builder();
        return new InMemoryUserDetailsManager(
                users.username("user1").password(passwordEncoder().encode("password")).roles("USER").build(),
                users.username("admin").password(passwordEncoder().encode("admin")).roles("ADMIN").build()
        );
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/tasks/**").authenticated() // доступ только аутентифицированным пользователям
                        .anyRequest().permitAll()
                )
                .formLogin(form -> form
                        .loginPage("/login") // Кастомная страница входа (можно указать свою)
                        .permitAll()
                )
                .logout(logout -> logout.permitAll()) // Настройка выхода
                .csrf(csrf -> csrf.disable()); // Отключаем CSRF для простоты тестирования (не для продакшн)

        return http.build();
    }
}
