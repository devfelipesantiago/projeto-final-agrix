package com.betrybe.agrix.ebytr.staff.service;

import com.betrybe.agrix.ebytr.staff.entity.Fertilizer;
import com.betrybe.agrix.ebytr.staff.exception.FertilizerNotFoundException;
import com.betrybe.agrix.ebytr.staff.repository.FertilizerRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Serviço responsável por operações relacionadas aos fertilizantes.
 */
@Service
public class FertilizerService {

  private final FertilizerRepository fertilizerRepository;

  /**
   * Construtor da classe FertilizerService.
   *
   * @param fertilizerRepository Repositório de fertilizantes para acesso aos dados.
   */
  @Autowired
  public FertilizerService(FertilizerRepository fertilizerRepository) {
    this.fertilizerRepository = fertilizerRepository;
  }

  /**
   * Retorna uma lista de todos os fertilizantes cadastrados.
   *
   * @return Lista de {@link Fertilizer}.
   */
  public List<Fertilizer> findAllFertilizer() {
    return fertilizerRepository.findAll();
  }

  /**
   * Salva um novo fertilizante no banco de dados.
   *
   * @param fertilizer O fertilizante a ser salvo.
   * @return O fertilizante salvo.
   */
  public Fertilizer saveFertilizer(Fertilizer fertilizer) {
    return fertilizerRepository.save(fertilizer);
  }

  /**
   * Busca um fertilizante pelo ID.
   *
   * @param id O ID do fertilizante a ser recuperado.
   * @return O fertilizante encontrado.
   * @throws FertilizerNotFoundException Se o fertilizante não for encontrado.
   */
  public Fertilizer getFertilizerById(Long id) throws FertilizerNotFoundException {
    return fertilizerRepository.findById(id).orElseThrow(FertilizerNotFoundException::new);
  }
}

