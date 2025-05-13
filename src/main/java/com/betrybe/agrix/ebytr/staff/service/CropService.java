package com.betrybe.agrix.ebytr.staff.service;

import com.betrybe.agrix.ebytr.staff.entity.Crop;
import com.betrybe.agrix.ebytr.staff.entity.Farm;
import com.betrybe.agrix.ebytr.staff.entity.Fertilizer;
import com.betrybe.agrix.ebytr.staff.exception.CropNotFoundException;
import com.betrybe.agrix.ebytr.staff.exception.FarmNotFoundException;
import com.betrybe.agrix.ebytr.staff.exception.FertilizerNotFoundException;
import com.betrybe.agrix.ebytr.staff.repository.CropRepository;
import com.betrybe.agrix.ebytr.staff.repository.FarmsRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Serviço responsável pelas operações relacionadas às plantações. Contém métodos para buscar,
 * criar, atualizar e recuperar plantações associadas a uma fazenda.
 */
@Service
public class CropService {

  @Autowired
  private final CropRepository cropRepository;

  @Autowired
  private final FarmsRepository farmsRepository;

  @Autowired
  private final FertilizerService fertilizerService;

  /**
   * Construtor do serviço CropService.
   *
   * @param cropRepository Repositório responsável pela persistência de dados das plantações.
   */
  public CropService(CropRepository cropRepository, FarmsRepository farmsRepository,
      FertilizerService fertilizerService) {
    this.cropRepository = cropRepository;
    this.farmsRepository = farmsRepository;
    this.fertilizerService = fertilizerService;
  }

  /**
   * Busca uma plantação pelo seu ID.
   *
   * @param cropId O identificador único da plantação.
   * @return Um {@link Optional} contendo a plantação encontrada ou vazio se não existir.
   */
  public Crop findById(Integer cropId) throws CropNotFoundException {
    return cropRepository.findById(cropId).orElseThrow(CropNotFoundException::new);
  }

  /**
   * Cria uma nova plantação no sistema e a associa a uma fazenda.
   *
   * @param crop    A entidade da plantação a ser criada.
   * @param farmsId O identificador da fazenda à qual a plantação será associada.
   * @return A plantação criada e persistida.
   */
  public Crop createCrop(Crop crop, Integer farmsId) throws FarmNotFoundException {
    Farm farm = farmsRepository.findById(farmsId)
        .orElseThrow(FarmNotFoundException::new);
    crop.setFarm(farm);
    return cropRepository.save(crop);
  }

  /**
   * Atualiza uma plantação existente no sistema.
   *
   * @param cropId O identificador da plantação a ser atualizada.
   * @param crop   A entidade da plantação com as informações atualizadas.
   * @return A entidade da plantação atualizada.
   */
  public Crop updateCrop(Integer cropId, Crop crop) throws CropNotFoundException {
    Crop oldCrop = findById(cropId);
    oldCrop.setName(crop.getName());
    oldCrop.setPlantedArea(crop.getPlantedArea());

    cropRepository.save(oldCrop);
    return oldCrop;
  }

  /**
   * Recupera a plantação associada a uma fazenda pelo seu ID.
   *
   * @param farmsId O identificador único da fazenda.
   * @return A plantação associada à fazenda.
   */
  public List<Crop> getAllCropsForFarmId(Integer farmsId) throws FarmNotFoundException {
    farmsRepository.findById(farmsId).orElseThrow(FarmNotFoundException::new);
    return cropRepository.findByFarmId(farmsId);
  }

  /**
   * Retorna uma lista de plantações cuja data de colheita está dentro do intervalo informado.
   *
   * @param start Data inicial do intervalo de colheita.
   * @param end   Data final do intervalo de colheita.
   * @return Lista de {@link Crop} cuja data de colheita está entre as datas fornecidas.
   */
  public List<Crop> getAllCropByPlantedDateBetweenHarvestDate(LocalDate start, LocalDate end) {
    List<Crop> cropList = cropRepository.findAll();
    return cropList.stream()
        .filter(crop -> crop.getHarvestDate() != null
            && !crop.getHarvestDate().isBefore(start)
            && !crop.getHarvestDate().isAfter(end))
        .toList();
  }

  /**
   * Associa um fertilizante a uma plantação específica.
   *
   * @param cropId       O ID da plantação à qual o fertilizante será associado.
   * @param fertilizerId O ID do fertilizante a ser adicionado.
   * @return Uma mensagem indicando o sucesso da operação.
   * @throws CropNotFoundException       Se a plantação não for encontrada.
   * @throws FertilizerNotFoundException Se o fertilizante não for encontrado.
   */
  public String saveFertilizerWithCrop(Integer cropId, Long fertilizerId)
      throws CropNotFoundException, FertilizerNotFoundException {
    Crop crop = findById(cropId);
    Fertilizer fertilizer = fertilizerService.getFertilizerById(fertilizerId);
    crop.getFertilizers().add(fertilizer);
    cropRepository.save(crop);
    return "Fertilizante e plantação associados com sucesso!";
  }

  /**
   * Retorna uma lista de todas as plantações cadastradas.
   *
   * @return Uma lista de objetos {@link Crop} representando todas as plantações encontradas.
   */
  public List<Crop> getAllCrops() {
    return cropRepository.findAll();
  }

  /**
   * Retrieves a crop with its associated fertilizers by the crop's ID.
   *
   * @param cropId The ID of the crop to retrieve
   * @return The crop entity with its fertilizers loaded
   * @throws IllegalArgumentException If cropId is null
   * @throws CropNotFoundException    If no crop with the given ID exists
   */
  public Optional<Crop> findFertilizerByCropId(Integer cropId) throws CropNotFoundException {
    return cropRepository.findByIdWithFertilizers(cropId);
  }
}
