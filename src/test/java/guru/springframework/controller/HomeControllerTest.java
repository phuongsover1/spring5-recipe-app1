package guru.springframework.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.configuration.MockAnnotationProcessor;
import org.springframework.ui.Model;

import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeServices;

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
