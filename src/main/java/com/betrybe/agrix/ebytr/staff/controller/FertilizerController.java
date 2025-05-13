package com.betrybe.agrix.ebytr.staff.controller;

import com.betrybe.agrix.ebytr.staff.controller.dto.FertilizerDto;
import com.betrybe.agrix.ebytr.staff.entity.Fertilizer;
import com.betrybe.agrix.ebytr.staff.exception.FertilizerNotFoundException;
import com.betrybe.agrix.ebytr.staff.service.FertilizerService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador responsável pelo gerenciamento de fertilizantes.
 */
@RestController
@RequestMapping("/fertilizers")
public class FertilizerController {

  private final FertilizerService fertilizerService;

  /**
   * Construtor da classe FertilizerController.
   *
   * @param fertilizerService Serviço responsável pelas operações com fertilizantes.
   */
  public FertilizerController(FertilizerService fertilizerService) {
    this.fertilizerService = fertilizerService;
  }

  /**
   * Adiciona um novo fertilizante.
   *
   * @param fertilizer O objeto {@link Fertilizer} a ser salvo.
   * @return {@link FertilizerDto} representando o fertilizante salvo.
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public FertilizerDto addFertilizer(@RequestBody Fertilizer fertilizer) {
    return FertilizerDto.fromEntity(fertilizerService.saveFertilizer(fertilizer));
  }

  /**
   * Retorna uma lista de todos os fertilizantes cadastrados.
   *
   * @return Lista de {@link FertilizerDto} representando os fertilizantes encontrados.
   */
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  @Secured("ROLE_ADMIN")
  public List<FertilizerDto> allFertilizer() {
    return fertilizerService.findAllFertilizer()
        .stream()
        .map(FertilizerDto::fromEntity)
        .toList();
  }

  /**
   * Retorna um fertilizante específico pelo ID.
   *
   * @param id O ID do fertilizante a ser recuperado.
   * @return {@link FertilizerDto} representando o fertilizante encontrado.
   * @throws FertilizerNotFoundException Se o fertilizante não for encontrado.
   */
  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public FertilizerDto fertilizerById(
      @PathVariable Long id) throws FertilizerNotFoundException {
    return FertilizerDto.fromEntity(fertilizerService.getFertilizerById(id));
  }
}

