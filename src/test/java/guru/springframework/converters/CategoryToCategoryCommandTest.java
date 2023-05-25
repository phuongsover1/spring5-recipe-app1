package guru.springframework.converters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.domain.Category;

public class CategoryToCategoryCommandTest {

  private CategoryToCategoryCommand categoryToCategoryCommand;

  @BeforeEach
  void setUp() {
    categoryToCategoryCommand = new CategoryToCategoryCommand();
  }

  @Test
  void testNull() {
    assertNull(categoryToCategoryCommand.convert(null));
  }

  @Test
  void testEmptyObj() {
    assertNotNull(categoryToCategoryCommand.convert(new Category()));
  }

  @Test
  void testConvert() {
    Category category = new Category();
    category.setId(1L);
    category.setDescription("category descriptiopn");

    CategoryCommand categoryCommand = categoryToCategoryCommand.convert(category);

    assertNotNull(categoryCommand);
    assertEquals(category.getId(), categoryCommand.getId());
    assertEquals(category.getDescription(), categoryCommand.getDescription());
  }
}
