package com.betrybe.agrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal da aplicação Agrix. Esta classe é responsável por iniciar a
 * aplicação Spring
 * Boot.
 */
@SpringBootApplication
public class AgrixApplication {

  /**
   * Método principal da aplicação. Este método é o ponto de entrada da aplicação
   * e inicia o Spring
   * Boot.
   *
   * @param args Os argumentos de linha de comando (se houver).
   */
  public static void main(String[] args) {
    SpringApplication.run(AgrixApplication.class, args);
  }

}