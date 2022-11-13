package guru.springframework.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;

public class RecipeServicesImplTest {

  RecipeServicesImpl recipeServicesImpl;

  @Mock
  RecipeRepository recipeRepository;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);

    recipeServicesImpl = new RecipeServicesImpl(recipeRepository);
  }

  @Test
  void testFindAll() {
    // giả lập database
    Recipe recipe = new Recipe();
    HashSet<Recipe> recipeData = new HashSet<>();
    recipeData.add(recipe);

    // setting hàm findAll() nếu được gọi thì trả về database mà ta đã thiết lập
    // ở trên
    when(recipeRepository.findAll()).thenReturn(recipeData);

    // thử gọi hàm findAll() trong recipeServicesImpl xem có đúng là hàm được gọi
    // đúng
    // recipeRepository ở trong không -> nếu đúng thì list trả về sẽ có object
    // RecipeData đã được tạo ở trên
    Set<Recipe> recipes = (Set<Recipe>) recipeServicesImpl.findAll();

    assertEquals(false, recipes.contains(recipe));

    recipeServicesImpl.findAll();
    verify(recipeRepository, times(2)).findAll();
  }
}
