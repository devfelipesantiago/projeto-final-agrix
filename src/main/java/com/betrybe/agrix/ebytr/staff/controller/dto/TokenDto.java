package com.betrybe.agrix.ebytr.staff.controller.dto;

/**
 * DTO (Data Transfer Object) que representa o token JWT gerado após autenticação bem-sucedida.
 * Este token é utilizado para autenticar futuras requisições do usuário.
 *
 * @param token O token JWT em formato String.
 */
public record TokenDto(String token) {

}

