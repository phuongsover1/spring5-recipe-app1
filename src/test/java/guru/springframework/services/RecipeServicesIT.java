package guru.springframework.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.converters.RecipeCommandToRecipe;
import guru.springframework.converters.RecipeToRecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;

@SpringBootTest
public class RecipeServicesIT {
  private static final String NEW_DESCRIPTION = "New Description";

  @Autowired
  RecipeServices recipeServices;

  @Autowired
  RecipeRepository recipeRepository;

  @Autowired
  RecipeCommandToRecipe recipeCommandToRecipe;

  @Autowired
  RecipeToRecipeCommand recipeToRecipeCommand;

  @Transactional
  @Test
  void testSaveOfDescription() throws Exception {
    // given
    Iterable<Recipe> recipes = recipeRepository.findAll();
    Recipe testRecipe = recipes.iterator().next();
    RecipeCommand testRecipeCommand = recipeToRecipeCommand.convert(testRecipe);
    assertNotNull(testRecipeCommand);

    // when
    testRecipeCommand.setDescription(NEW_DESCRIPTION);
    RecipeCommand saveRecipeCommand = recipeServices.saveRecipeCommand(testRecipeCommand);

    // then
    assertEquals(NEW_DESCRIPTION, saveRecipeCommand.getDescription());
    assertEquals(testRecipe.getId(), testRecipeCommand.getId());
    assertEquals(testRecipe.getIngredients().size(), testRecipeCommand.getIngredients().size());
    assertEquals(testRecipe.getCategories().size(), testRecipeCommand.getCategories().size());

  }
}
