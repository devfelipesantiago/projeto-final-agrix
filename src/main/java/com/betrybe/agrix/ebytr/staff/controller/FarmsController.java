package com.betrybe.agrix.ebytr.staff.controller;

import com.betrybe.agrix.ebytr.staff.controller.dto.CropDto;
import com.betrybe.agrix.ebytr.staff.controller.dto.FarmDto;
import com.betrybe.agrix.ebytr.staff.entity.Crop;
import com.betrybe.agrix.ebytr.staff.entity.Farm;
import com.betrybe.agrix.ebytr.staff.exception.FarmNotFoundException;
import com.betrybe.agrix.ebytr.staff.service.CropService;
import com.betrybe.agrix.ebytr.staff.service.FarmsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller responsável por gerenciar as operações relacionadas às fazendas.
 */
@RestController
@RequestMapping(value = "/farms")
public class FarmsController {

  @Autowired
  private final FarmsService farmsService;
  @Autowired
  private final CropService cropService;

  /**
   * Construtor da classe FarmsController.
   *
   * @param farmsService serviço responsável pelas operações relacionadas às
   *                     fazendas
   * @param cropService  serviço responsável pelas operações relacionadas às
   *                     plantações
   */
  public FarmsController(FarmsService farmsService, CropService cropService) {
    this.farmsService = farmsService;
    this.cropService = cropService;
  }

  /**
   * Cria uma nova fazenda.
   *
   * @param farm objeto da fazenda a ser criado
   * @return o objeto da fazenda criada
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public FarmDto createFarm(@RequestBody Farm farm) {
    farmsService.createFarm(farm);
    return FarmDto.fromEntity(farm);
  }

  /**
   * Recupera todas as fazendas cadastradas.
   *
   * @return uma lista de todas as fazendas
   */
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<FarmDto> getAllFarms() {
    List<Farm> farms = farmsService.findAll();
    return farms.stream().map(FarmDto::fromEntity).toList();
  }

  /**
   * Recupera uma fazenda pelo seu ID.
   *
   * @param id o ID da fazenda a ser recuperada
   * @return a fazenda correspondente ao ID fornecido
   * @throws FarmNotFoundException se a fazenda não for encontrada
   */
  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public FarmDto getFarmById(@PathVariable() Integer id) throws FarmNotFoundException {
    return FarmDto.fromEntity(farmsService.findById(id));
  }

  /**
   * Cria uma nova plantação associada a uma fazenda.
   *
   * @param crop    a plantação a ser criada
   * @param farmsId o ID da fazenda à qual a plantação será associada
   * @return a plantação criada
   */
  @PostMapping("/{farmsId}/crops")
  @ResponseStatus(HttpStatus.CREATED)
  public CropDto createCrop(@RequestBody Crop crop, @PathVariable("farmsId") Integer farmsId)
      throws FarmNotFoundException {
    return CropDto.fromEntity(cropService.createCrop(crop, farmsId));
  }

  /**
   * Recupera todas as plantações associadas a uma fazenda.
   *
   * @param farmsId o ID da fazenda para a qual as plantações serão recuperadas
   * @return a lista de plantações associadas à fazenda
   */
  @GetMapping("/{farmsId}/crops")
  @ResponseStatus(HttpStatus.OK)
  public List<CropDto> getAllCropsForFarmId(@PathVariable("farmsId") Integer farmsId)
      throws FarmNotFoundException {
    return cropService.getAllCropsForFarmId(farmsId).stream().map(CropDto::fromEntity).toList();
  }
}