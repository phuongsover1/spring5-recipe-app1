package guru.springframework.converters;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RecipeToRecipeCommandTest {

  @Mock
  CategoryToCategoryCommand categoryCommand;

  @Mock
  NotesToNotesCommand notesCommand;

  @Mock
  IngredientToIngredientCommand ingredientCommand;

  @InjectMocks
  RecipeToRecipeCommand recipeCommand;

  @Test
  void testConvert() {
    RecipeCommand converted = recipeCommand.convert(new Recipe());
    assertNotNull(converted);
  }
}
