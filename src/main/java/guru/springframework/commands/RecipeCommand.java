package guru.springframework.commands;

import guru.springframework.domain.Difficulty;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RecipeCommand {

  private Long id;
  private String description;
  private Integer prepTime;
  private Integer cookTime;
  private Integer servings;
  private String source;
  private String url;
  private String directions;

  private Set<CategoryCommand> categories = new HashSet<>();
  private Difficulty difficulty;

  private Byte[] image;
  private NotesCommand notes;
  private Set<IngredientCommand> ingredients = new HashSet<>();

  public RecipeCommand addIngredient(IngredientCommand ingre) {
    ingredients.add(ingre);
    ingre.setRecipe(this);
    return this;
  }
}
