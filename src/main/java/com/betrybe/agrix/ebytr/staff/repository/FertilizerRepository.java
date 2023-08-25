package com.betrybe.agrix.ebytr.staff.repository;

import com.betrybe.agrix.ebytr.staff.entity.Fertilizer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface de repositório para a entidade Fertilizer.
 */
public interface FertilizerRepository extends JpaRepository<Fertilizer, Integer> {

}
