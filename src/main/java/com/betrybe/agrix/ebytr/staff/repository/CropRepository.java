package com.betrybe.agrix.ebytr.staff.repository;

import com.betrybe.agrix.ebytr.staff.entity.Crop;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Interface que define o repositório para operações de acesso a dados relacionados à entidade
 * Crop.
 */
public interface CropRepository extends JpaRepository<Crop, Integer> {
  @Query("SELECT c FROM Crop c WHERE c.farm.id = :farmId")
  List<Crop> getCrops(@Param("farmId") Integer farmId);

  /**
   * Busca as plantações cuja data de colheita esteja entre as datas de início e de fim.
   *
   * @param start Data de início da busca.
   * @param end   Data de fim da busca.
   * @return Uma lista de plantações.
   */
  List<Crop> findByHarvestDateBetween(LocalDate start, LocalDate end);
}
