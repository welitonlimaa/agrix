package com.agrix.staff.repository;

import com.agrix.staff.entity.Fertilizer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface de repositório para a entidade Fertilizer.
 */
public interface FertilizerRepository extends JpaRepository<Fertilizer, Integer> {

}
