package guru.springframework.services;

import java.util.Optional;
import java.util.Set;

import guru.springframework.domain.Category;
import guru.springframework.domain.Difficulty;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Notes;
import guru.springframework.domain.Recipe;

public interface RecipeServices {
  Recipe save(Recipe recipe);

  Optional<Recipe> findByDescription(String description);

  Recipe createRecipe(String description, Integer prepTime, Integer cookTime, Integer servings, String source,
      String url,
      String directions, Set<Category> categories, Difficulty difficulty, Byte[] image, Notes notes,
      Set<Ingredient> ingredients);

  Recipe createRecipe(String description, Integer prepTime, Integer cookTime, Integer servings, String source,
      String url,
      String directions, Set<Category> categories, Difficulty difficulty, Notes notes, Set<Ingredient> ingredients);

}
