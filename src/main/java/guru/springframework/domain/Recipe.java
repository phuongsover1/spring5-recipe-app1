package guru.springframework.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;

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
  private Set<Category> categories;

  @Enumerated(value = EnumType.STRING)
  private Difficulty difficulty;
  @Lob
  private Byte[] image;

  @OneToOne(cascade = CascadeType.ALL)
  private Notes notes;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
  private Set<Ingredient> ingredients;

  public Recipe() {
  }

  @Override
  public String toString() {
    return "Recipe [id=" + id + ", description=" + description + ", prepTime=" + prepTime + ", cookTime=" + cookTime
        + ", servings=" + servings + ", source=" + source + ", url=" + url + ", directions=" + directions
        + ", categories=" + categories + ", difficulty=" + difficulty + ", notes=" + notes + ", ingredients="
        + ingredients + "]";
  }

  public Recipe(String description, Integer prepTime, Integer cookTime, Integer servings, String source, String url,
      String directions, Set<Category> categories, Difficulty difficulty, Byte[] image, Notes notes,
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
    this.ingredients = ingredients;
  }

  public Recipe(String description, Integer prepTime, Integer cookTime, Integer servings, String source, String url,
      String directions, Set<Category> categories, Difficulty difficulty, Notes notes, Set<Ingredient> ingredients) {
    this(description, prepTime, cookTime, servings, source, url, directions, categories, difficulty, null, notes,
        ingredients);
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getPrepTime() {
    return prepTime;
  }

  public void setPrepTime(Integer prepTime) {
    this.prepTime = prepTime;
  }

  public Integer getCookTime() {
    return cookTime;
  }

  public void setCookTime(Integer cookTime) {
    this.cookTime = cookTime;
  }

  public Integer getServings() {
    return servings;
  }

  public void setServings(Integer servings) {
    this.servings = servings;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getDirections() {
    return directions;
  }

  public void setDirections(String directions) {
    this.directions = directions;
  }

  public Byte[] getImage() {
    return image;
  }

  public void setImage(Byte[] image) {
    this.image = image;
  }

  public Notes getNotes() {
    return notes;
  }

  public void setNotes(Notes notes) {
    this.notes = notes;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Set<Category> getCategories() {
    return categories;
  }

  public void setCategories(Set<Category> categories) {
    this.categories = categories;
  }

  public Difficulty getDifficulty() {
    return difficulty;
  }

  public void setDifficulty(Difficulty difficulty) {
    this.difficulty = difficulty;
  }

  public Set<Ingredient> getIngredients() {
    return ingredients;
  }

  public void setIngredients(Set<Ingredient> ingredients) {
    this.ingredients = ingredients;
  }
}
