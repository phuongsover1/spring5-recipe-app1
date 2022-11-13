package guru.springframework.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import guru.springframework.domain.UnitOfMeasure;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UnitOfMeasureRepositoryIntegrationTest {
  @Autowired
  UnitOfMeasureRepository unitOfMeasureRepository;

  @Test
  @DirtiesContext
  void testFindByDescription() {
    Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

    assertEquals("Teaspoon", uomOptional.get().getDescription()); // true
    // assertEquals("Tablespoon", uomOptional.get().getDescription()); // false
  }

  @Test
  void testFindByDescriptionCup() {
    Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findByDescription("Cup");

    assertEquals("Cup", uomOptional.get().getDescription());
  }
}
