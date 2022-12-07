package guru.springframework.converters;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

  private final CategoryToCategoryCommand categoryConverter;
  private final NotesToNotesCommand notesConverter;
  private final IngredientToIngredientCommand ingredientConverter;

  @Autowired
  public RecipeToRecipeCommand(
    CategoryToCategoryCommand categoryConverter,
    NotesToNotesCommand notesConverter,
    IngredientToIngredientCommand ingredientConverter
  ) {
    this.categoryConverter = categoryConverter;
    this.notesConverter = notesConverter;
    this.ingredientConverter = ingredientConverter;
  }

  @Synchronized
  @Override
  @Nullable
  public RecipeCommand convert(Recipe source) {
    if (source == null) return null;
    final RecipeCommand recipe = new RecipeCommand();
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
    source
      .getCategories()
      .forEach(category ->
        recipe.getCategories().add(categoryConverter.convert(category))
      );

    source
      .getIngredients()
      .forEach(ingredient ->
        recipe.getIngredients().add(ingredientConverter.convert(ingredient))
      );

    return recipe;
  }
}
