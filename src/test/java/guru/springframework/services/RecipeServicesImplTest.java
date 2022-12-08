package guru.springframework.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RecipeServicesImplTest {

  @InjectMocks
  RecipeServicesImpl recipeServicesImpl;

  @Mock
  RecipeRepository recipeRepository;

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

    assertEquals(true, recipes.contains(recipe));

    recipeServicesImpl.findAll();
    verify(recipeRepository, times(2)).findAll();
  }

  @Test
  void testFindById() {
    // create sample recipe to retrieve when using findById() method in
    // recipeServicesImpl
    Recipe tempRecipe = new Recipe();
    tempRecipe.setId(4L);
    Optional<Recipe> recipeOptional = Optional.of(tempRecipe);

    when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

    Recipe returnedRecipe = recipeServicesImpl.findById(4L);

    // assert not null when calling findById()
    assertNotNull(returnedRecipe);
    assertEquals(returnedRecipe, tempRecipe);

    // assert findById was called exact once before reaching this code
    verify(recipeRepository, times(1)).findById(anyLong());
    // assert findAll was not called
    verify(recipeRepository, never()).findAll();
  }

  @Test
  void testDeleteById() {
    // given
    Long idToDelete = Long.valueOf("2");

    // when
    recipeServicesImpl.deleteById(idToDelete);

    // no when since the deleteById is void type method

    // then
    verify(recipeRepository, times(1)).deleteById(anyLong());
  }
}
