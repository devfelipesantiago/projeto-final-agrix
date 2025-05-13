package com.betrybe.agrix.ebytr.staff.controller.dto;

/**
 * DTO (Data Transfer Object) utilizado para encapsular as credenciais de autenticação.
 * Contém o nome de usuário e a senha fornecidos no momento do login.
 *
 * @param username Nome de usuário.
 * @param password Senha do usuário.
 */
public record AuthDto(String username, String password) {

}
