package com.agrix.staff.dto;

import com.agrix.staff.entity.Fertilizer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO que representa um fertilizante para comunicação com a camada de serviços.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FertilizerDto {

  /**
   * Identificador único do fertilizante.
   */
  private Integer id;

  /**
   * Nome do fertilizante.
   */
  private String name;

  /**
   * Marca do fertilizante.
   */
  private String brand;

  /**
   * Composição do fertilizante.
   */
  private String composition;

  /**
   * Construtor que recebe um objeto Fertilizer para inicializar o DTO.
   *
   * @param fertilizer O fertilizante do qual os dados serão copiados.
   */
  public FertilizerDto(Fertilizer fertilizer) {
    id = fertilizer.getId();
    name = fertilizer.getName();
    brand = fertilizer.getBrand();
    composition = fertilizer.getComposition();
  }
}
