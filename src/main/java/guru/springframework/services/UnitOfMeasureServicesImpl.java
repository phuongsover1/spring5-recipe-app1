package guru.springframework.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.UnitOfMeasureRepository;

@Service
public class UnitOfMeasureServicesImpl implements UnitOfMeasureServices {

  private final UnitOfMeasureRepository unitOfMeasureRepository;

  @Autowired
  public UnitOfMeasureServicesImpl(UnitOfMeasureRepository unitOfMeasureRepository) {
    this.unitOfMeasureRepository = unitOfMeasureRepository;
  }

  @Override
  public UnitOfMeasure save(UnitOfMeasure unitOfMeasure) {
    if (unitOfMeasure != null) {
      return unitOfMeasureRepository.save(unitOfMeasure);
    }
    return unitOfMeasure;
  }

  @Override
  public UnitOfMeasure createUnitOfMeasure(String description) {
    return new UnitOfMeasure(description);
  }

  @Override
  public Optional<UnitOfMeasure> findByDescription(String description) {
    return unitOfMeasureRepository.findByDescription(description);
  }

}
