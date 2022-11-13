package guru.springframework.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeServices;

@ExtendWith(MockitoExtension.class)
public class HomeControllerTest {

  HomeController homeController;

  @Mock
  RecipeServices recipeServices;

  @Mock
  Model model;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);

    homeController = new HomeController(recipeServices);
  }

  @Test
  void testMockMVC() throws Exception {
    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();

    mockMvc.perform(MockMvcRequestBuilders.get("/"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.view().name("index"));
  }

  @Test
  void testGetIndexPage() {
    // given
    Set<Recipe> recipes = new HashSet<>();
    recipes.add(new Recipe());

    Recipe recipe = new Recipe();
    recipe.setId(3L);
    recipes.add(recipe);

    when(recipeServices.findAll()).thenReturn(recipes);

    ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

    homeController.getIndexPage(model); // argument was captured after call the method in this line

    verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());

    // Watch the capture values
    assertEquals(2, argumentCaptor.getValue().size());
  }
}
