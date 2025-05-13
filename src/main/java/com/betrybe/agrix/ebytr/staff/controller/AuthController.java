package com.betrybe.agrix.ebytr.staff.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.betrybe.agrix.ebytr.staff.controller.dto.AuthDto;
import com.betrybe.agrix.ebytr.staff.controller.dto.TokenDto;
import com.betrybe.agrix.ebytr.staff.service.TokenService;

@RestController
@RequestMapping("/auth")
public class AuthController {

  private final AuthenticationManager authenticationManager;
  private final TokenService tokenService;

  @Autowired
  public AuthController(AuthenticationManager authenticationManager, TokenService tokenService) {
    this.authenticationManager = authenticationManager;
    this.tokenService = tokenService;
  }

  @PostMapping("/login")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<String> login(@RequestBody AuthDto authDto) {
    try {
      UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(authDto.username(),
          authDto.password());

      Authentication auth = authenticationManager.authenticate(usernamePassword);
      String token = tokenService.generateToken(auth.getName());

      return new TokenDto(token);

    } catch (BadCredentialsException ex) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Usuário ou senha inválidos");
    }
  }
}