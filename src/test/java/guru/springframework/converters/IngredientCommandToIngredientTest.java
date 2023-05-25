package guru.springframework.converters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.Ingredient;

public class IngredientCommandToIngredientTest {

  private static final Long ID = 1L;
  private static final String DESCRIPTION = "MY INGREDIENT";
  private static final BigDecimal AMOUNT = new BigDecimal(0.3);

  IngredientCommandToIngredient iCommandToIngredient;

  @BeforeEach
  void setUp() {
    iCommandToIngredient = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
  }

  @Test
  void testNullParameter() {
    assertNull(iCommandToIngredient.convert(null));
  }

  @Test
  void testConvert() {
    // given
    IngredientCommand iCommand = new IngredientCommand(ID, DESCRIPTION, AMOUNT, null,
        new UnitOfMeasureCommand(2L, "MG"));

    Ingredient ingredient = iCommandToIngredient.convert(iCommand);

    assertNotNull(ingredient);
    assertEquals(ID, ingredient.getId());
    assertEquals(DESCRIPTION, ingredient.getDescription());
    assertEquals(AMOUNT, ingredient.getAmount());
    assertEquals(2L, ingredient.getUom().getId());
    assertEquals("MG", ingredient.getUom().getDescription());

  }
}