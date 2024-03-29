package guru.springframework.converters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Category;
import guru.springframework.domain.Difficulty;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Notes;
import guru.springframework.domain.Recipe;
import org.junit.jupiter.api.Test;

public class RecipeToRecipeCommandTest {

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

  RecipeToRecipeCommand convertToRecipeCommand;

  @BeforeEach
  void setUp() {
    convertToRecipeCommand = new RecipeToRecipeCommand(new CategoryToCategoryCommand(), new NotesToNotesCommand(),
        new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()));
  }

  @Test
  void testNullParameter() {
    assertNull(convertToRecipeCommand.convert(null));
  }

  @Test
  void testEmptyRecipe() {
    RecipeCommand converted = convertToRecipeCommand.convert(new Recipe());
    assertNotNull(converted);
  }

  @Test
  void testConvert() {
    // given
    Recipe recipe = new Recipe();
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

    Category category_1 = new Category();
    category_1.setId(CAT_ID_1);

    Category category_2 = new Category();
    category_2.setId(CAT_ID_2);

    Set<Category> categories = new HashSet<>();
    categories.add(category_1);
    categories.add(category_2);

    recipe.setCategories(categories);

    Notes notes = new Notes();
    notes.setId(NOTES_ID);
    recipe.setNotes(notes);

    Ingredient ingre_1 = new Ingredient();
    ingre_1.setId(INGRE_ID_1);
    Ingredient ingre_2 = new Ingredient();
    ingre_2.setId(INGRE_ID_2);

    recipe.addIngredient(ingre_1);
    recipe.addIngredient(ingre_2);

    RecipeCommand recipeCommand = convertToRecipeCommand.convert(recipe);

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
