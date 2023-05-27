package guru.springframework.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.converters.IngredientToIngredientCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class IngredientServicesImpl implements IngredientServices {
  private final RecipeRepository recipeRepository;
  private final IngredientToIngredientCommand ingredientToIngredientCommand;
  private static final IngredientCommand emptyIngredient = new IngredientCommand();

  public IngredientServicesImpl(RecipeRepository recipeRepository,
      IngredientToIngredientCommand ingredientToIngredientCommand) {
    this.recipeRepository = recipeRepository;
    this.ingredientToIngredientCommand = ingredientToIngredientCommand;
  }

  @Override
  public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
    Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
    if (!recipeOptional.isPresent()) {
      log.debug("Recipe not found. ID: " + recipeId);
      return emptyIngredient;
    }

    Recipe recipe = recipeOptional.get();
    Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
        .filter(ingredient -> ingredient.getId().equals(ingredientId))
        .map(ingredient -> ingredientToIngredientCommand.convert(ingredient))
        .findFirst();

    if (!ingredientCommandOptional.isPresent()) {
      {
        log.debug("Ingredient not found. ID: " + ingredientId);
      }
    }
    return ingredientCommandOptional.orElse(emptyIngredient);
  }

}
