package com.agrix.staff.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Classe que representa um fertilizante utilizado em plantações.
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Fertilizer {

  /**
   * Identificador único do fertilizante.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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
   * Lista de culturas (plantações) associadas a este fertilizante.
   */
  @ManyToMany(mappedBy = "fertilizers")
  private List<Crop> crops;

}

