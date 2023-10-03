package com.agrix.staff.controller;

import com.agrix.staff.dto.CropDto;
import com.agrix.staff.dto.FarmDto;
import com.agrix.staff.service.CropService;
import com.agrix.staff.service.FarmService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador responsável pelas operações relacionadas às fazendas.
 */
@RestController
@RequestMapping("/farms")
public class FarmController {

  private final FarmService farmService;
  private final CropService cropService;

  @Autowired
  public FarmController(FarmService farmService, CropService cropService) {
    this.farmService = farmService;
    this.cropService = cropService;
  }

  /**
   * Cria uma nova fazenda.
   *
   * @param farmDto Os dados da fazenda a serem criados.
   * @return Uma resposta contendo os dados da fazenda criada e o status 201
   */
  @PostMapping
  public ResponseEntity<FarmDto> createFarm(@RequestBody FarmDto farmDto) {
    FarmDto createdFarm = farmService.createFarm(farmDto);
    return new ResponseEntity<>(createdFarm, HttpStatus.CREATED);
  }

  /**
   * Obtém uma lista de todas as fazendas.
   *
   * @return Uma resposta contendo uma lista de fazendas status 200
   */
  @GetMapping
  @Secured({"USER", "MANAGER", "ADMIN"})
  public ResponseEntity<List<FarmDto>> getAllFarms() {
    List<FarmDto> farms = farmService.getAllFarms();
    return ResponseEntity.ok(farms);
  }

  /**
   * Obtém os detalhes de uma fazenda pelo seu ID.
   *
   * @param id O ID da fazenda a ser buscada.
   * @return Uma resposta contendo um objeto representando os detalhes da fazenda com o status HTTP
   *        200, ou o status HTTP 404 se a fazenda não for encontrada.
   */
  @GetMapping("/{id}")
  public ResponseEntity<FarmDto> getFarmById(@PathVariable Integer id) {
    FarmDto farmDto = farmService.getFarmById(id);
    return ResponseEntity.ok(farmDto);
  }

  /**
   * Cria uma nova plantação associada a uma fazenda existente.
   *
   * @param farmId  O ID da fazenda à qual a plantação será associada.
   * @param cropDto Os dados da plantação a serem criados.
   * @return Um CropDto representando a plantação criada e o status HTTP 201.
   */
  @PostMapping("/{farmId}/crops")
  public ResponseEntity<CropDto> createCrop(@PathVariable Integer farmId,
      @RequestBody CropDto cropDto) {
    CropDto createdCrop = cropService.createCrop(farmId, cropDto);
    return new ResponseEntity<>(createdCrop, HttpStatus.CREATED);
  }

  /**
   * Lista as plantações associadas a uma fazenda específica.
   *
   * @param farmId O ID da fazenda cujas plantações serão listadas.
   * @return Uma resposta contendo uma lista de plantações associadas à
   *        fazenda e o status HTTP 200.
   */
  @GetMapping("/{farmId}/crops")
  public ResponseEntity<List<CropDto>> getFarmsCrops(@PathVariable Integer farmId) {
    List<CropDto> crops = cropService.getFarmsCrops(farmId);
    return ResponseEntity.ok(crops);
  }


}
