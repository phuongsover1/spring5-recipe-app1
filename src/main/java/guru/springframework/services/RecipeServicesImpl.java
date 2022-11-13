package guru.springframework.services;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import guru.springframework.domain.Category;
import guru.springframework.domain.Difficulty;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Notes;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RecipeServicesImpl implements RecipeServices {

  private final RecipeRepository recipeRepository;

  @Autowired
  public RecipeServicesImpl(RecipeRepository recipeRepository) {
    this.recipeRepository = recipeRepository;
  }

  @Override
  public Recipe save(Recipe recipe) {
    if (recipe != null) {
      return recipeRepository.save(recipe);
    }

    return recipe;
  }

  @Override
  public Optional<Recipe> findByDescription(String description) {
    return recipeRepository.findByDescription(description);
  }

  @Override
  public Recipe createRecipe(String description, Integer prepTime, Integer cookTime, Integer servings, String source,
      String url, String directions, Set<Category> categories, Difficulty difficulty, Byte[] image, Notes notes,
      Set<Ingredient> ingredients) {
    return new Recipe(description, prepTime, cookTime, servings, source, url, directions, categories, difficulty, image,
        notes, ingredients);
  }

  @Override
  public Recipe createRecipe(String description, Integer prepTime, Integer cookTime, Integer servings, String source,
      String url, String directions, Set<Category> categories, Difficulty difficulty, Notes notes,
      Set<Ingredient> ingredients) {
    return new Recipe(description, prepTime, cookTime, servings, source, url, directions, categories, difficulty, null,
        notes, ingredients);
  }

  @Override
  public Set<Recipe> findAll() {
    log.debug("I'm in the service.");
    Iterable<Recipe> recipeIterable = recipeRepository.findAll();
    int size = ((Collection<?>) recipeIterable).size();

    Set<Recipe> recipes = new HashSet<>(size);
    recipeIterable.forEach(recipes::add);
    return recipes;
  }

}
