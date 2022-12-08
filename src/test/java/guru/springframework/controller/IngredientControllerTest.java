package guru.springframework.controller;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.services.RecipeServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class IngredientControllerTest {

  @Mock
  RecipeServices recipeService;

  IngredientController controller;

  MockMvc mockMvc;

  @BeforeEach
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);

    controller = new IngredientController(recipeService);
    mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
  }

  @Test
  public void testListIngredients() throws Exception {
    //given
    RecipeCommand recipeCommand = new RecipeCommand();
    when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);

    //when
    mockMvc
      .perform(get("/recipe/1/ingredients"))
      .andExpect(status().isOk())
      .andExpect(view().name("recipe/ingredient/list"))
      .andExpect(model().attributeExists("recipe"));

    //then
    verify(recipeService, times(1)).findCommandById(anyLong());
  }
}
