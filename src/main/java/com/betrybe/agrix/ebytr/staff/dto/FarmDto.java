package com.betrybe.agrix.ebytr.staff.dto;

import com.betrybe.agrix.ebytr.staff.entity.Farm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Classe de transferência de dados (DTO) para informações de fazenda.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FarmDto {

  private Integer id;
  private String name;
  private double size;

  /**
   * Construtor que recebe um objeto Farm e popula os atributos do DTO com os dados fornecidos pela
   * fazenda.
   *
   * @param farm A fazenda da qual os dados serão copiados.
   */
  public FarmDto(Farm farm) {
    id = farm.getId();
    name = farm.getName();
    size = farm.getSize();
  }
}
