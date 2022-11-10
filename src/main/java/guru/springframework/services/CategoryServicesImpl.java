package guru.springframework.services;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import guru.springframework.domain.Category;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.CategoryRepository;

@Service
public class CategoryServicesImpl implements CategoryServices {

  private final CategoryRepository categoryRepository;

  @Autowired
  public CategoryServicesImpl(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  @Override
  public Category save(Category category) {
    if (category != null)
      return categoryRepository.save(category);
    return category;
  }

  @Override
  public Optional<Category> findByDescription(String description) {

    return categoryRepository.findByDescription(description);
  }

  @Override
  public Category createCategory(String description, Set<Recipe> recipes) {
    return new Category(description, recipes);
  }

  @Override
  public Category createCategory(String description) {
    return new Category(description);
  }

}
