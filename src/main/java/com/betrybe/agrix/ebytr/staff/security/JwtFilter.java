package com.betrybe.agrix.ebytr.staff.security;

import com.betrybe.agrix.ebytr.staff.service.PersonService;
import com.betrybe.agrix.ebytr.staff.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Filtro JWT que intercepta requisições HTTP para verificar e validar tokens JWT.
 * Se um token válido for encontrado, autentica o usuário no contexto de segurança do Spring.
 */
@Component
public class JwtFilter extends OncePerRequestFilter {

  private final TokenService tokenService;
  private final PersonService personService;

  /**
   * Construtor da classe JwtFilter.
   *
   * @param tokenService   Serviço responsável pela validação de tokens JWT.
   * @param personService  Serviço responsável pelo carregamento de usuários a partir do token.
   */
  @Autowired
  public JwtFilter(TokenService tokenService, PersonService personService) {
    this.tokenService = tokenService;
    this.personService = personService;
  }

  /**
   * Método que intercepta a requisição HTTP, extrai o token JWT e realiza a autenticação
   * do usuário, se o token for válido.
   *
   * @param request      Requisição HTTP.
   * @param response     Resposta HTTP.
   * @param filterChain  Cadeia de filtros do Spring.
   * @throws ServletException Em caso de erro ao processar o filtro.
   * @throws IOException      Em caso de erro de I/O.
   */
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    Optional<String> token = extractToken(request);

    if (token.isPresent()) {
      String subject = tokenService.validateToken(token.get());

      UserDetails userDetails = personService.loadUserByUsername(subject);

      UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
          userDetails, null, userDetails.getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    filterChain.doFilter(request, response);
  }

  /**
   * Extrai o token JWT do cabeçalho Authorization da requisição HTTP.
   *
   * @param request Requisição HTTP.
   * @return Um Optional contendo o token JWT, se presente.
   */
  private Optional<String> extractToken(HttpServletRequest request) {
    String authHeader = request.getHeader("Authorization");

    if (authHeader == null) {
      return Optional.empty();
    }

    return Optional.of(authHeader.replace("Bearer", "").trim());
  }
}
