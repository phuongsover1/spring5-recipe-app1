package guru.springframework.converters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import guru.springframework.commands.IngredientCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.UnitOfMeasure;

public class IngredientToIngredientCommandTest {

  private static final Long ID = 1L;
  private static final String DESCRIPTION = "MY INGREDIENT";
  private static final BigDecimal AMOUNT = new BigDecimal(0.3);
  private static final UnitOfMeasure U_MEASURE = new UnitOfMeasure(2L, "MG");

  IngredientToIngredientCommand ingredientConverter;

  @BeforeEach
  void setUp() {
    ingredientConverter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
  }

  @Test
  void testNullParameter() {
    assertNull(ingredientConverter.convert(null));
  }

  @Test
  void testConvert() {
    Ingredient ingredient = new Ingredient(ID, DESCRIPTION, AMOUNT, U_MEASURE);

    IngredientCommand iCommand = ingredientConverter.convert(ingredient);

    assertNotNull(iCommand);
    assertEquals(ID, iCommand.getId());
    assertEquals(DESCRIPTION, iCommand.getDescription());
    assertEquals(AMOUNT, iCommand.getAmount());
    assertEquals(U_MEASURE.getId(), iCommand.getUom().getId());
    assertEquals(U_MEASURE.getDescription(), iCommand.getUom().getDescription());

  }
}
