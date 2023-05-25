package guru.springframework.converters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.UnitOfMeasure;

public class UnitOfMeasureCommandToUnitOfMeasureTest {

  private static final Long ID = 1L;
  private static final String DESCRIPTION = "mg";

  private UnitOfMeasureCommandToUnitOfMeasure uom;

  @BeforeEach
  public void setUp() {
    uom = new UnitOfMeasureCommandToUnitOfMeasure();
  }

  @Test
  public void testNullParameter() {
    assertNull(uom.convert(null));
  }

  @Test
  public void testEmptyObject() {
    assertNotNull(uom.convert(new UnitOfMeasureCommand()));
  }

  @Test
  public void testNotEmptyObject() {
    UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
    unitOfMeasureCommand.setId(ID);
    unitOfMeasureCommand.setDescription(DESCRIPTION);

    UnitOfMeasure uomConverted = uom.convert(unitOfMeasureCommand);

    assertNotNull(uomConverted);
    assertEquals(unitOfMeasureCommand.getId(), uomConverted.getId());
    assertEquals(unitOfMeasureCommand.getDescription(), uomConverted.getDescription());

  }

}
