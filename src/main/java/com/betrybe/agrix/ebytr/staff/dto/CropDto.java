package com.betrybe.agrix.ebytr.staff.dto;

import com.betrybe.agrix.ebytr.staff.entity.Crop;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO que representa um plantio para comunicação com a camada de serviços.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CropDto {

  /**
   * Identificador único do plantio.
   */
  private Integer id;

  /**
   * Nome do plantio.
   */
  private String name;

  /**
   * Área plantada.
   */
  private double plantedArea;

  /**
   * Data em que a plantação foi semeada.
   */
  private LocalDate plantedDate;

  /**
   * Data em que a plantação foi ou está prevista para ser colhida.
   */
  private LocalDate harvestDate;

  /**
   * Identificador da fazenda à qual este plantio está associado.
   */
  private Integer farmId;

  /**
   * Construtor que recebe um objeto Crop para inicializar o DTO.
   *
   * @param crop A plantação da qual os dados serão copiados.
   */
  public CropDto(Crop crop) {
    id = crop.getId();
    name = crop.getName();
    plantedArea = crop.getPlantedArea();
    plantedDate = crop.getPlantedDate();
    harvestDate = crop.getHarvestDate();
    farmId = crop.getFarm().getId();
  }
}
