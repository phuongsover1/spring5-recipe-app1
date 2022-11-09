package guru.springframework.services;

import java.util.Optional;
import java.util.Set;

import guru.springframework.domain.Category;
import guru.springframework.domain.Recipe;

public interface CategoryServices {
  Category save(Category category);

  Optional<Category> findByDescription(String description);

  Category createCategory(String description, Set<Recipe> recipes);

  Category createCategory(String description);
}
