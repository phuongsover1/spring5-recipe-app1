package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Category;
import guru.springframework.domain.Recipe;
import java.util.HashSet;
import java.util.Set;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

  private final CategoryCommandToCategory categoryConverter;
  private final NotesCommandToNotes notesConverter;
  private final IngredientCommandToIngredient ingredientConverter;

  @Autowired
  public RecipeCommandToRecipe(
    CategoryCommandToCategory categoryConverter,
    NotesCommandToNotes notesConverter,
    IngredientCommandToIngredient ingredientConverter
  ) {
    this.categoryConverter = categoryConverter;
    this.notesConverter = notesConverter;
    this.ingredientConverter = ingredientConverter;
  }

  @Synchronized
  @Override
  @Nullable
  public Recipe convert(RecipeCommand source) {
    if (source == null) return null;
    final Recipe recipe = new Recipe();
    recipe.setId(source.getId());
    recipe.setDescription(source.getDescription());
    recipe.setPrepTime(source.getPrepTime());
    recipe.setCookTime(source.getCookTime());
    recipe.setServings(source.getServings());
    recipe.setSource(source.getSource());
    recipe.setUrl(source.getUrl());
    recipe.setDirections(source.getDirections());
    recipe.setDifficulty(source.getDifficulty());
    recipe.setImage(source.getImage());
    recipe.setNotes(notesConverter.convert(source.getNotes()));

    Set<CategoryCommand> sourceCategories = source.getCategories();
    if (sourceCategories != null && sourceCategories.size() > 0) {
      source
        .getCategories()
        .forEach(category ->
          recipe.getCategories().add(categoryConverter.convert(category))
        );
    }

    Set<IngredientCommand> sourceIngredients = source.getIngredients();
    if (sourceIngredients != null && sourceIngredients.size() > 0) {
      source
        .getIngredients()
        .forEach(ingredient ->
          recipe.getIngredients().add(ingredientConverter.convert(ingredient))
        );
    }

    return recipe;
  }
}
