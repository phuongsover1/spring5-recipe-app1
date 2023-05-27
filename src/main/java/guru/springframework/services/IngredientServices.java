package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;

public interface IngredientServices {
  IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
}
