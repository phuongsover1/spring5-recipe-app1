package guru.springframework.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.configuration.MockAnnotationProcessor;
import org.springframework.ui.Model;

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
    // assert that the getIndexPage method return a string ?
    String viewName = homeController.getIndexPage(model);
    assertEquals("index", viewName);

    verify(recipeServices, times(1)).findAll();
    verify(model, times(1)).addAttribute(eq("recipes"), anySet());

  }
}
