package com.betrybe.agrix.ebytr.staff.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Serviço responsável por gerar e validar tokens JWT para autenticação de usuários.
 */
@Service
public class TokenService {

  private final Algorithm algorithm;

  /**
   * Construtor que inicializa o algoritmo de assinatura HMAC256 com a chave secreta fornecida.
   *
   * @param secret Chave secreta usada para assinar/verificar tokens JWT.
   */
  public TokenService(@Value("${api.security.token.secret}") String secret) {
    this.algorithm = Algorithm.HMAC256(secret);
  }

  /**
   * Gera um token JWT assinado com o nome de usuário como "subject" e validade de 2 horas.
   *
   * @param username Nome de usuário que será usado como subject do token.
   * @return Token JWT gerado como String.
   */
  public String generateToken(String username) {
    return JWT.create()
        .withSubject(username)
        .withExpiresAt(generateExpiration())
        .sign(algorithm);
  }

  /**
   * Gera o instante de expiração do token, com 2 horas a partir do momento atual.
   *
   * @return Objeto {@link Instant} representando o tempo de expiração.
   */
  private Instant generateExpiration() {
    return Instant.now()
        .plus(2, ChronoUnit.HOURS);
  }

  /**
   * Valida um token JWT assinado e retorna o subject (nome de usuário) contido nele.
   *
   * @param token Token JWT a ser validado.
   * @return Subject (usuário) extraído do token.
   */
  public String validateToken(String token) {
    return JWT.require(algorithm)
        .build()
        .verify(token)
        .getSubject();
  }
}
