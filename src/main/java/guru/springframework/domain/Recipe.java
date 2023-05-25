package guru.springframework.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class Recipe {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String description;
  private Integer prepTime;
  private Integer cookTime;
  private Integer servings;
  private String source;
  private String url;
  private String directions;

  // TODO add
  // private Difficulty difficulty

  @ManyToMany
  @JoinTable(name = "recipe_category", joinColumns = @JoinColumn(name = "recipe_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
  private Set<Category> categories = new HashSet<>();

  @Enumerated(value = EnumType.STRING)
  private Difficulty difficulty;

  @Lob
  private Byte[] image;

  @OneToOne(cascade = CascadeType.ALL)
  private Notes notes;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
  private Set<Ingredient> ingredients = new HashSet<>();

  @Builder
  public Recipe(
      String description,
      Integer prepTime,
      Integer cookTime,
      Integer servings,
      String source,
      String url,
      String directions,
      Set<Category> categories,
      Difficulty difficulty,
      Byte[] image,
      Notes notes,
      Set<Ingredient> ingredients) {
    this.description = description;
    this.prepTime = prepTime;
    this.cookTime = cookTime;
    this.servings = servings;
    this.source = source;
    this.url = url;
    this.directions = directions;
    this.categories = categories;
    this.difficulty = difficulty;
    this.image = image;
    this.notes = notes;
    ingredients.forEach(ingredient -> addIngredient(ingredient));
  }

  public Recipe(
      String description,
      Integer prepTime,
      Integer cookTime,
      Integer servings,
      String source,
      String url,
      String directions,
      Set<Category> categories,
      Difficulty difficulty,
      Notes notes,
      Set<Ingredient> ingredients) {
    this(
        description,
        prepTime,
        cookTime,
        servings,
        source,
        url,
        directions,
        categories,
        difficulty,
        null,
        notes,
        ingredients);
  }

  public Recipe addIngredient(Ingredient ingredient) {
    this.ingredients.add(ingredient);
    ingredient.setRecipe(this);
    return this;
  }

}
