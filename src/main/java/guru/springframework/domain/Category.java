package guru.springframework.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@EqualsAndHashCode(exclude = {"recipes"})
@Data
@Entity
public class Category {

  @Builder
  public Category(Long id, String description) {
    this.id = id;
    this.description = description;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String description;

  @ManyToMany(mappedBy = "categories")
  private Set<Recipe> recipes;

  public Category(String description, Set<Recipe> recipes) {
    this.description = description;
    this.recipes = recipes;
  }

  public Category(String description) {
    this(description, new HashSet<>());
  }
}
