package com.agrix.staff.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Classe que representa um plantio em uma fazenda.
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Crop {

  /**
   * Identificador único da cultura.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  /**
   * Nome da cultura.
   */
  private String name;

  /**
   * Área plantada da cultura.
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
   * Fazenda à qual esta cultura está associada.
   */
  @ManyToOne
  private Farm farm;

  /**
   * Lista de fertilizantes associados a esta cultura.
   */
  @ManyToMany
  @JoinTable(
      name = "crop_fertilizers",
      joinColumns = @JoinColumn(name = "crop_id"),
      inverseJoinColumns = @JoinColumn(name = "fertilizer_id")
  )
  private List<Fertilizer> fertilizers;
}
