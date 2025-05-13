package com.betrybe.agrix.ebytr.staff.repository;

import com.betrybe.agrix.ebytr.staff.entity.Fertilizer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório JPA para a entidade {@link Fertilizer}.
 * Esta interface permite operações CRUD básicas e consultas personalizadas
 * relacionadas à entidade {@link Fertilizer} no banco de dados.
 */
public interface FertilizerRepository extends JpaRepository<Fertilizer, Long> {

}
