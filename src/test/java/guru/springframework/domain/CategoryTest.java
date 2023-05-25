package guru.springframework.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CategoryTest {
  Category category;

  @BeforeEach
  public void setUp() {
    category = Category.builder().id(3L).build();
  }

  @Test
  void testGetDescription() {}

  @Test
  void testGetId() {
    category.setId(4l);
    assertEquals(4L, category.getId());
  }

  @Test
  void testGetRecipes() {}
}
