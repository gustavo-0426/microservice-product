package com.co.softworld.configuration;

import com.co.softworld.dao.IUserDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(aut -> aut
                        .requestMatchers(HttpMethod.GET, "/microservice/product").hasAuthority("ROLE_admin")
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public UserDetailsService userDetailsService(IUserDao userDao) throws Exception {

        return username -> userDao.findByUsername(username)
                .map(user -> User.builder()
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .authorities(user.getAuthorities())
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found"));
    }


    /*
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/microservices/product/authorized").permitAll()
                        .requestMatchers(GET, "/microservices/product", "/microservices/product/findById/{id}").hasAnyAuthority("SCOPE_read", "SCOPE_write")
                        .requestMatchers(POST, "/microservices/product").hasAuthority("SCOPE_write")
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .oauth2Login(login -> login
                        .loginPage("oauth2/authorization/oidc-client")
                )
                .oauth2Client(Customizer.withDefaults()
                )
                .oauth2ResourceServer(resource -> resource
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(new JwtAuthenticationConverter()))
                );
        return http.build();
    }

     */


}
