package guru.springframework.controller;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.services.IngredientServices;
import guru.springframework.services.RecipeServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

@ExtendWith(MockitoExtension.class)
public class IngredientControllerTest {

  @Mock
  RecipeServices recipeService;

  @Mock
  IngredientServices ingredientServices;

  @Mock
  Model model;

  @InjectMocks
  IngredientController controller;

  MockMvc mockMvc;

  @BeforeEach
  public void setUp() throws Exception {
    mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
  }

  @Test
  public void testListIngredients() throws Exception {
    // given
    RecipeCommand recipeCommand = new RecipeCommand();
    when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);

    // when
    mockMvc
        .perform(get("/recipe/1/ingredients"))
        .andExpect(status().isOk())
        .andExpect(view().name("recipe/ingredient/list"))
        .andExpect(model().attributeExists("recipe"));

    // then
    verify(recipeService, times(1)).findCommandById(anyLong());
  }

  @Test
  void testGetIngredient() throws Exception {
    // bỏ vào một recipe command có 1 Ingredient command trong đó, kết quả khi trả
    // về là phải trùng với view name , và phải có 1 object trong
    // model attribute

    // given
    RecipeCommand recipeCommand = new RecipeCommand();
    recipeCommand.setId(2L);

    IngredientCommand ingre1 = new IngredientCommand();
    ingre1.setId(1L);
    ingre1.setDescription("Ingre1");
    UnitOfMeasureCommand uom = new UnitOfMeasureCommand();
    uom.setId(1L);
    ingre1.setUom(uom);

    recipeCommand.addIngredient(ingre1);

    IngredientCommand ingre2 = new IngredientCommand();
    ingre2.setId(2L);
    ingre2.setDescription("Ingre2");
    UnitOfMeasureCommand uom2 = new UnitOfMeasureCommand();
    uom.setId(2L);
    ingre2.setUom(uom2);

    recipeCommand.addIngredient(ingre2);

    // when
    when(ingredientServices.findByRecipeIdAndIngredientId(1L, 2L)).thenReturn(ingre2);

    // then

    mockMvc.perform(get("/recipe/1/ingredients/2/show"))
        .andExpect(model().attributeExists("ingredient"))
        .andExpect(model().attribute("ingredient", ingre2))
        .andExpect(status().isOk())
        .andExpect(view().name("recipe/ingredient/show"));

  }

}
