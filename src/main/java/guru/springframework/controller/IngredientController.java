package guru.springframework.controller;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.services.RecipeServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@Slf4j
public class IngredientController {

  private final RecipeServices recipeServices;

  @Autowired
  public IngredientController(RecipeServices recipeServices) {
    this.recipeServices = recipeServices;
  }

  @GetMapping("/recipe/{id}/ingredients")
  public String listIngredients(@PathVariable String id, Model model) {
    log.debug("In listIngredient mapping");
    RecipeCommand recipeCommand = recipeServices.findCommandById(
      Long.valueOf(id)
    );

    model.addAttribute("recipe", recipeCommand);
    return "recipe/ingredient/list";
  }
}
