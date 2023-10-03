package com.agrix.staff.repository;

import com.agrix.staff.entity.Farm;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface de repositório para acesso aos dados das fazendas.
 */
public interface FarmRepository extends JpaRepository<Farm, Integer> {

}
