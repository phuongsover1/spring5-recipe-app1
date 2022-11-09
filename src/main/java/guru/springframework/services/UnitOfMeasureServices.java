package guru.springframework.services;

import guru.springframework.domain.UnitOfMeasure;

public interface UnitOfMeasureServices {
  UnitOfMeasure save(UnitOfMeasure unitOfMeasure);

  UnitOfMeasure createUnitOfMeasure(String description);
}
