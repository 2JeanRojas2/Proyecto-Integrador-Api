package com.NewDestiny.config;

import com.NewDestiny.util.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtFilter jwtFilter;

    /**
     * Registrando BCryptPasswordEncoder para poder utilizarlo e implementarlo en todo el ecosistema
     * de spring boot.
     */
    @Bean
    private static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Inicializando y registrando nuestro metodo de autenticación, es decir, es el que nos da el contexto del uso
     * de los metodos de autenticación, es el gestor de autenticacion.
     * Se registra porque hay librerias de oauth2 y jwt que las implementan y aquí lo que estamos diciendo es que
     * use nuestro bean que es el que tiene el contexto de nuestra aplicación.
     */
    @Bean("authenticationManager")
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Registro la seguridad para que no sea una clase con estado ni una seguridad por sesiones
     * esto se hace a partir de mi metodo de autorización jwt
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().and().csrf().disable()
                .authorizeRequests()
                // Public endpoints
                .antMatchers("/categories/**").permitAll()
                .antMatchers("/cities/**").permitAll()
                .antMatchers("/features/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/products/**").permitAll()
                .antMatchers("/roles/**").permitAll()
                .antMatchers("/users/**").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/resendVerifyToken").permitAll()
                .antMatchers("/verifyRegistration").permitAll()
                .antMatchers("/schedules/**").permitAll()
                .antMatchers("/authenticate").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().headers().xssProtection().block(false).disable();
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

    }

}



