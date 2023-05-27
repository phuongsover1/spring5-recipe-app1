package guru.springframework.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.converters.IngredientToIngredientCommand;
import guru.springframework.converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;

@ExtendWith(MockitoExtension.class)
public class IngredientServicesImplTest {

  @Mock
  private RecipeRepository recipeRepository;

  private IngredientToIngredientCommand ingredientToIngredientCommand;

  private IngredientServices ingredientServices;

  private final IngredientCommand emptyIngredientCommand = new IngredientCommand();

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    ingredientServices = new IngredientServicesImpl(recipeRepository, ingredientToIngredientCommand);
  }

  @Test
  void testFindByRecipeIdAndIngredientId() {
    // giờ ta tạo ra một Recipe có id 1L, trong đó có 3 cái
    // Ingredient 1L, 2L, 3L
    // when findRecipeById mà là 3L -> trả về empty Ingredient
    // when findRecipeById mà là 1L -> trả về recipe mà ta vừa mới tạo

    // given
    Recipe recipe = new Recipe();
    recipe.setId(1L);

    Ingredient ingredient = new Ingredient();
    ingredient.setId(1L);
    recipe.addIngredient(ingredient);

    ingredient = new Ingredient();
    ingredient.setId(2L);
    recipe.addIngredient(ingredient);

    ingredient = new Ingredient();
    ingredient.setId(3L);
    recipe.addIngredient(ingredient);

    Optional<Recipe> recipeOptional = Optional.of(recipe);

    Recipe emptyRecipe = new Recipe();
    Optional<Recipe> emptyRecipeOptional = Optional.of(emptyRecipe);

    // when
    when(recipeRepository.findById(1L)).thenReturn(recipeOptional);
    when(recipeRepository.findById(3L)).thenReturn(emptyRecipeOptional);

    IngredientCommand emptyCommand = ingredientServices.findByRecipeIdAndIngredientId(3L, 3L);
    IngredientCommand ingredientCommand = ingredientServices.findByRecipeIdAndIngredientId(1L, 2L);

    // then
    assertNotNull(emptyCommand);
    assertEquals(null, emptyCommand.getId());
    assertNotNull(ingredientCommand);
    assertEquals(2L, ingredientCommand.getId());
  }
}
