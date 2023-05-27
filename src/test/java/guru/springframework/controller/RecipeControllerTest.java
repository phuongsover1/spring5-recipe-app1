package guru.springframework.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
// import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

@ExtendWith(MockitoExtension.class)
public class RecipeControllerTest {

  @InjectMocks
  RecipeController controller;

  @Mock
  RecipeServices recipeServices;

  @Mock
  Model model;

  MockMvc mockMvc;

  @BeforeEach
  public void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
  }

  @Test
  void testGetRecipe() throws Exception {
    Recipe tempRecipe = new Recipe();
    tempRecipe.setId(1L);

    when(recipeServices.findById(anyLong())).thenReturn(tempRecipe);

    ArgumentCaptor<Recipe> recipeCaptor = ArgumentCaptor.forClass(Recipe.class);

    controller.getRecipe("1", model);
    verify(recipeServices, times(1)).findById(anyLong());

    // capture
    verify(model, times(1)).addAttribute(eq("recipe"), recipeCaptor.capture());

    // watch the value of the capture variable
    assertEquals(tempRecipe, recipeCaptor.getValue());

    mockMvc
        .perform(get("/recipe/1/show"))
        .andExpect(status().isOk())
        .andExpect(view().name("recipe/show"))
        .andExpect(model().attributeExists("recipe"));
  }

  @Test
  void testDeleteRecipe() throws Exception {
    // given
    Long idToDelete = Long.valueOf("2");

    mockMvc
        .perform(get("/recipe/" + idToDelete + "/delete"))
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/"));
  }

  @Test
  void testPostRecipe() throws Exception {
    RecipeCommand command = new RecipeCommand();
    command.setId(2L);

    when(recipeServices.saveRecipeCommand(any())).thenReturn(command);

    mockMvc
        .perform(MockMvcRequestBuilders.post("/recipe").contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("id", "").param("description", "some string"))
        .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
        .andExpect(MockMvcResultMatchers.view().name("redirect:/recipe/show/2"));

  }

  @Test
  void testUpdateRecipe() throws Exception {
    RecipeCommand command = new RecipeCommand();
    command.setId(1L);

    when(recipeServices.findCommandById(anyLong())).thenReturn(command);

    mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/update"))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.model().attributeExists("recipe"))
        .andExpect(MockMvcResultMatchers.view().name("recipe/recipeform"));
  }
}
