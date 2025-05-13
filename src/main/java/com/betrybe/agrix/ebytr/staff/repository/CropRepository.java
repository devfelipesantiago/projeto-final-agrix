package com.betrybe.agrix.ebytr.staff.repository;

import com.betrybe.agrix.ebytr.staff.entity.Crop;
import java.util.List;
import java.util.Optional;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repositório para operações de persistência relacionadas à entidade {@link Crop}. Extende a
 * interface {@link JpaRepository} para fornecer funcionalidades CRUD básicas para a entidade Crop.
 */
@Repository
public interface CropRepository extends JpaRepository<Crop, Integer> {

  /**
   * Busca todas as plantações associadas a uma fazenda específica.
   *
   * @param farmsId O identificador da fazenda.
   * @return Uma lista de plantações associadas à fazenda.
   */
  List<Crop> findByFarmId(Integer farmsId);

  /**
   * Finds a crop by its ID and eagerly fetches its associated fertilizers.
   *
   * @param id The ID of the crop to find (must not be null)
   * @return An Optional containing the crop with fertilizers if found, or empty if not found
   */
  @Query("SELECT c FROM Crop c "
      + "LEFT JOIN FETCH c.fertilizers "
      + "WHERE c.id = :id")
  Optional<Crop> findByIdWithFertilizers(@Param("id") Integer id);

}