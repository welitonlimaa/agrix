package com.betrybe.agrix.ebytr.staff.controller;

import com.betrybe.agrix.ebytr.staff.dto.FertilizerDto;
import com.betrybe.agrix.ebytr.staff.service.FertilizerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador responsável por lidar com as requisições relacionadas a fertilizantes.
 */
@RestController
@RequestMapping("/fertilizers")
public class FertilizerController {

  private final FertilizerService fertilizerService;

  @Autowired
  public FertilizerController(FertilizerService fertilizerService) {
    this.fertilizerService = fertilizerService;
  }

  /**
   * Cria um novo fertilizante.
   *
   * @param fertilizerDto Os dados do fertilizante a serem criados.
   * @return O fertilizante criado com status 201.
   */
  @PostMapping
  public ResponseEntity<FertilizerDto> createFertilizer(@RequestBody FertilizerDto fertilizerDto) {
    FertilizerDto createdFertilizer = fertilizerService.createFertilizer(fertilizerDto);
    return new ResponseEntity<>(createdFertilizer, HttpStatus.CREATED);
  }

  /**
   * Obtém uma lista de todos os fertilizantes cadastrados.
   *
   * @return Uma resposta contendo uma lista de fertilizantes e o status HTTP 200.
   */
  @GetMapping
  public ResponseEntity<List<FertilizerDto>> getAllFertilizers() {
    List<FertilizerDto> fertilizers = fertilizerService.getAllFertilizers();
    return ResponseEntity.ok(fertilizers);
  }

  /**
   * Obtém as informações de um fertilizante pelo seu ID.
   *
   * @param id O ID do fertilizante a ser buscado.
   * @return Uma resposta contendo as informações do fertilizante com o status
   *        HTTP 200, ou o status HTTP 404 se o fertilizante não for encontrado.
   */
  @GetMapping("/{id}")
  public ResponseEntity<FertilizerDto> getFertilizerById(@PathVariable Integer id) {
    FertilizerDto fertilizerDto = fertilizerService.getFertilizerById(id);
    return ResponseEntity.ok(fertilizerDto);
  }
}
