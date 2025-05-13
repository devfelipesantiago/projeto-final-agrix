package com.betrybe.agrix.ebytr.staff.service;

import com.betrybe.agrix.ebytr.staff.entity.Farm;
import com.betrybe.agrix.ebytr.staff.exception.FarmNotFoundException;
import com.betrybe.agrix.ebytr.staff.repository.FarmsRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service responsável pelas operações relacionadas às fazendas. Esta classe contém métodos para
 * criar, buscar, listar e atualizar fazendas.
 */
@Service
public class FarmsService {

  @Autowired
  private final FarmsRepository farmsRepository;

  /**
   * Construtor da classe FarmsService.
   *
   * @param farmsRepository Repositório responsável pela persistência de dados das fazendas.
   */
  public FarmsService(FarmsRepository farmsRepository) {
    this.farmsRepository = farmsRepository;
  }

  /**
   * Cria uma nova fazenda no sistema.
   *
   * @param farm A entidade da fazenda a ser criada.
   * @return A fazenda criada e persistida.
   */
  public Farm createFarm(Farm farm) {
    return farmsRepository.save(farm);
  }

  /**
   * Busca uma fazenda pelo seu ID.
   *
   * @param id O identificador único da fazenda.
   * @return A fazenda encontrada, se existir.
   * @throws FarmNotFoundException Se a fazenda não for encontrada.
   */
  public Farm findById(Integer id) throws FarmNotFoundException {
    return farmsRepository.findById(id)
        .orElseThrow(FarmNotFoundException::new);
  }

  /**
   * Busca todas as fazendas cadastradas no sistema.
   *
   * @return Uma lista contendo todas as fazendas.
   */
  public List<Farm> findAll() {
    return farmsRepository.findAll();
  }

  /**
   * Atualiza uma fazenda existente no sistema. Os dados atualizados incluem tamanho, nome e cultura
   * da fazenda.
   *
   * @param farm A entidade da fazenda com as informações atualizadas.
   * @param id   O identificador único da fazenda a ser atualizada.
   * @return A entidade da fazenda atualizada.
   * @throws FarmNotFoundException Se a fazenda com o ID fornecido não for encontrada.
   */
  public Farm updateFarm(Farm farm, Integer id) throws FarmNotFoundException {
    Farm farmUpdated = findById(id);

    farmUpdated.setSize(farm.getSize());
    farmUpdated.setName(farm.getName());
    farmUpdated.setCrops(farm.getCrops());

    farmsRepository.save(farmUpdated);
    return farmUpdated;
  }
}
