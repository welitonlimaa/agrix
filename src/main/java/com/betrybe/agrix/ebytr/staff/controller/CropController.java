package com.betrybe.agrix.ebytr.staff.controller;

import com.betrybe.agrix.ebytr.staff.dto.CropDto;
import com.betrybe.agrix.ebytr.staff.dto.FertilizerDto;
import com.betrybe.agrix.ebytr.staff.dto.ResponseDto;
import com.betrybe.agrix.ebytr.staff.service.CropService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador responsável por lidar com as requisições relacionadas a plantações.
 */
@RestController
@RequestMapping("/crops")
public class CropController {

  private final CropService cropService;

  @Autowired
  public CropController(CropService cropService) {
    this.cropService = cropService;
  }

  /**
   * Obtém uma lista de todas as plantações cadastradas.
   *
   * @return Os detalhes das lantações e o status 200.
   */
  @GetMapping
  public ResponseEntity<List<CropDto>> getAllCrops() {
    List<CropDto> crops = cropService.getAllCrops();
    return ResponseEntity.ok(crops);
  }

  /**
   * Obtém os detalhes de uma plantação pelo seu ID.
   *
   * @param id O ID da plantação a ser buscada.
   * @return Os detalhes da plantação com o status 200,
   *         ou o status HTTP 404 se a plantação não for encontrada.
   */
  @GetMapping("/{id}")
  public ResponseEntity<CropDto> getCropById(@PathVariable Integer id) {
    CropDto cropDto = cropService.getCropById(id);
    return ResponseEntity.ok(cropDto);
  }

  /**
   * Busca plantações a partir da data de colheita.
   *
   * @param start Data de início da busca.
   * @param end   Data de fim da busca.
   * @return Uma lista de plantações.
   */
  @GetMapping("/search")
  public ResponseEntity<List<CropDto>> searchCropsByHarvestDate(
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
    List<CropDto> crops = cropService.searchCropsByHarvestDate(start, end);
    return ResponseEntity.ok(crops);
  }

  /**
   * Associa um fertilizante a uma plantação.
   *
   * @param cropId       O ID da plantação.
   * @param fertilizerId O ID do fertilizante.
   * @return Uma resposta com a mensagem de sucesso.
   */
  @PostMapping("/{cropId}/fertilizers/{fertilizerId}")
  public ResponseEntity<ResponseDto> associateCropWithFertilizer(
      @PathVariable Integer cropId,
      @PathVariable Integer fertilizerId) {

    cropService.associateCropWithFertilizer(cropId, fertilizerId);

    ResponseDto responseDto = new ResponseDto(
        "Fertilizante e plantação associados com sucesso!",
        null
    );

    return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
  }

  /**
   * Obtém a lista de fertilizantes associados a uma plantação pelo seu ID.
   *
   * @param cropId O ID da plantação.
   * @return Uma lista contendo os detalhes dos fertilizantes com o status 200,
   *         ou o status HTTP 404 se a plantação não for encontrada.
   */
  @GetMapping("/{cropId}/fertilizers")
  public ResponseEntity<List<FertilizerDto>> getFertilizersByCropId(@PathVariable Integer cropId) {
    List<FertilizerDto> fertilizers = cropService.getFertilizersByCropId(cropId);

    return ResponseEntity.ok(fertilizers);
  }

}
