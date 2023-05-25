package guru.springframework.converters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.UnitOfMeasure;

public class UnitOfMeasureToUnitOfMeasureCommandTest {

  private static final Long ID = 1L;
  private static final String DESCRIPTION = "mg";
  private UnitOfMeasureToUnitOfMeasureCommand uom;

  @BeforeEach
  void setUp() {
    uom = new UnitOfMeasureToUnitOfMeasureCommand();
  }

  @Test
  void testNullParameter() {
    assertNull(uom.convert(null));
  }

  @Test
  void testEmptyObject() {
    assertNotNull(uom.convert(new UnitOfMeasure()));
  }

  @Test
  void testConvert() {
    UnitOfMeasure unitOfMeasure = UnitOfMeasure.builder().id(ID).description(DESCRIPTION).build();

    UnitOfMeasureCommand uomConverted = uom.convert(unitOfMeasure);

    assertNotNull(uomConverted);
    assertEquals(ID, uomConverted.getId());
    assertEquals(DESCRIPTION, uomConverted.getDescription());
  }
}
