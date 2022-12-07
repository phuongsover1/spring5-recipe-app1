package guru.springframework.controller;

import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/recipe")
public class RecipeController {

  private final RecipeServices recipeServices;

  @Autowired
  public RecipeController(RecipeServices recipeServices) {
    this.recipeServices = recipeServices;
  }

  @GetMapping("/show/{id}")
  public String getRecipe(@PathVariable String id, Model model) {
    Recipe recipe = recipeServices.findById(Long.parseLong(id));
    model.addAttribute("recipe", recipe);
    return "recipe/show";
  }
}
