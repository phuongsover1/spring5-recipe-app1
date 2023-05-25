package guru.springframework.converters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.NotesCommand;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Difficulty;
import guru.springframework.domain.Recipe;

public class RecipeCommandToRecipeTest {
  private static final Long RECIPE_ID = 1L;
  private static final Integer COOK_TIME = Integer.valueOf("5");
  private static final Integer PREP_TIME = Integer.valueOf("3");
  private static final String DESCRIPTION = "My Recipe";
  private static final String DIRECTIONS = "Directions";
  private static final Difficulty DIFFICULTY = Difficulty.EASY;
  private static final Integer SERVINGS = Integer.valueOf("3");
  private static final String SOURCE = "Source";
  private static final String URL = "Some URL";
  private static final Byte[] RECIPE_IMAGE = new Byte[2];
  private static final Long CAT_ID_1 = 1L;
  private static final Long CAT_ID_2 = 2L;
  private static final Long NOTES_ID = 1L;
  private static final Long INGRE_ID_1 = 1L;
  private static final Long INGRE_ID_2 = 2L;

  RecipeCommandToRecipe convertToRecipe;

  @BeforeEach
  void setUp() {
    convertToRecipe = new RecipeCommandToRecipe(new CategoryCommandToCategory(), new NotesCommandToNotes(),
        new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()));
  }

  @Test
  void testNullParameter() {
    assertNull(convertToRecipe.convert(null));
  }

  @Test
  void testEmptyRecipe() {
    Recipe converted = convertToRecipe.convert(new RecipeCommand());
    assertNotNull(converted);
  }

  @Test
  void testConvert() {
    // given
    RecipeCommand recipe = new RecipeCommand();
    recipe.setId(RECIPE_ID);
    recipe.setCookTime(COOK_TIME);
    recipe.setPrepTime(PREP_TIME);
    recipe.setDescription(DESCRIPTION);
    recipe.setDirections(DIRECTIONS);
    recipe.setDifficulty(DIFFICULTY);
    recipe.setServings(SERVINGS);
    recipe.setSource(SOURCE);
    recipe.setUrl(URL);
    recipe.setImage(RECIPE_IMAGE);

    CategoryCommand category_1 = new CategoryCommand();
    category_1.setId(CAT_ID_1);

    CategoryCommand category_2 = new CategoryCommand();
    category_2.setId(CAT_ID_2);

    Set<CategoryCommand> categories = new HashSet<>();
    categories.add(category_1);
    categories.add(category_2);

    recipe.setCategories(categories);

    NotesCommand notes = new NotesCommand();
    notes.setId(NOTES_ID);
    notes.setRecipeNotes(DESCRIPTION);
    recipe.setNotes(notes);

    IngredientCommand ingre_1 = new IngredientCommand();
    ingre_1.setId(INGRE_ID_1);
    IngredientCommand ingre_2 = new IngredientCommand();
    ingre_2.setId(INGRE_ID_2);

    recipe.addIngredient(ingre_1);
    recipe.addIngredient(ingre_2);

    Recipe recipeCommand = convertToRecipe.convert(recipe);
    assertNotNull(recipeCommand);
    assertEquals(RECIPE_ID, recipeCommand.getId());
    assertEquals(COOK_TIME, recipeCommand.getCookTime());
    assertEquals(PREP_TIME, recipeCommand.getPrepTime());
    assertEquals(DESCRIPTION, recipeCommand.getDescription());
    assertEquals(DIRECTIONS, recipeCommand.getDirections());
    assertEquals(DIFFICULTY, recipeCommand.getDifficulty());
    assertEquals(SERVINGS, recipeCommand.getServings());
    assertEquals(SOURCE, recipeCommand.getSource());
    assertEquals(URL, recipeCommand.getUrl());
    assertEquals(RECIPE_IMAGE.length, recipeCommand.getImage().length);
    assertEquals(2, recipeCommand.getCategories().size());
    assertEquals(2, recipeCommand.getIngredients().size());

  }
}
