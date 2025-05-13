package com.betrybe.agrix.ebytr.staff.security;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Classe de configuração da segurança da aplicação.
 * Define políticas de autenticação, autorização, uso de JWT e segurança da API.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

  private final JwtFilter jwtFilter;

  /**
   * Construtor que injeta o filtro JWT responsável por autenticar as requisições.
   *
   * @param jwtFilter Filtro de autenticação JWT.
   */
  @Autowired
  public SecurityConfig(JwtFilter jwtFilter) {
    this.jwtFilter = jwtFilter;
  }

  /**
   * Configura a cadeia de filtros de segurança da aplicação.
   * Define endpoints públicos, protege rotas privadas, desativa CSRF e usa sessões stateless.
   *
   * @param httpSecurity Objeto de configuração do Spring Security.
   * @return Cadeia de filtros de segurança configurada.
   * @throws Exception Em caso de erro de configuração.
   */
  @Bean
  public DefaultSecurityFilterChain securityWebFilterChain(HttpSecurity httpSecurity)
      throws Exception {
    return httpSecurity
        .csrf(AbstractHttpConfigurer::disable)
        .sessionManagement(
            session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        )
        .authorizeHttpRequests(authorize -> authorize
            .requestMatchers(HttpMethod.POST, "/persons").permitAll()
            .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
            .requestMatchers(toH2Console()).permitAll()
            .anyRequest().authenticated())
        .headers(header -> header.frameOptions(FrameOptionsConfig::sameOrigin))
        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
  }

  /**
   * Bean responsável por fornecer o {@link AuthenticationManager}, que é utilizado
   * para autenticar as credenciais dos usuários.
   *
   * @param authenticationConfiguration Configuração de autenticação do Spring.
   * @return Instância de AuthenticationManager.
   * @throws Exception Em caso de falha ao obter o gerenciador.
   */
  @Bean
  public AuthenticationManager authenticationManager(
      AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  /**
   * Bean responsável por codificar senhas usando o algoritmo BCrypt.
   *
   * @return Implementação de {@link PasswordEncoder} com BCrypt.
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
