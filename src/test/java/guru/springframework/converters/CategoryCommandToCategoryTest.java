package guru.springframework.converters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.domain.Category;

public class CategoryCommandToCategoryTest {
  private CategoryCommandToCategory categoryCommandToCategory;

  @BeforeEach
  void setUp() {
    categoryCommandToCategory = new CategoryCommandToCategory();
  }

  @Test
  void testNull() {
    assertNull(categoryCommandToCategory.convert(null));
  }

  @Test
  void testEmptyObj() {
    assertNotNull(categoryCommandToCategory.convert(new CategoryCommand()));
  }

  @Test
  void testConvert() {
    CategoryCommand categoryCommand = new CategoryCommand();
    categoryCommand.setId(1L);
    categoryCommand.setDescription("category descriptiopn");

    Category category = categoryCommandToCategory.convert(categoryCommand);

    assertNotNull(category);
    assertEquals(category.getId(), category.getId());
    assertEquals(category.getDescription(), category.getDescription());
  }
}
