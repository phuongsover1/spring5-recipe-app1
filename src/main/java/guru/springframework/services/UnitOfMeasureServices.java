package guru.springframework.services;

import java.util.Optional;

import guru.springframework.domain.UnitOfMeasure;

public interface UnitOfMeasureServices {
  UnitOfMeasure save(UnitOfMeasure unitOfMeasure);

  UnitOfMeasure createUnitOfMeasure(String description);

  Optional<UnitOfMeasure> findByDescription(String description);
}
