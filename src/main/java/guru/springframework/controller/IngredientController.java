package guru.springframework.controller;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.services.IngredientServices;
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
  private final IngredientServices ingredientServices;

  @Autowired
  public IngredientController(RecipeServices recipeServices, IngredientServices ingredientServices) {
    this.recipeServices = recipeServices;
    this.ingredientServices = ingredientServices;
  }

  @GetMapping("/recipe/{id}/ingredients")
  public String listIngredients(@PathVariable String id, Model model) {
    log.debug("In listIngredient mapping");
    RecipeCommand recipeCommand = recipeServices.findCommandById(
        Long.valueOf(id));

    model.addAttribute("recipe", recipeCommand);
    return "recipe/ingredient/list";
  }

  @GetMapping("/recipe/{recipeId}/ingredients/{ingredientId}/show")
  public String getIngredient(@PathVariable Long recipeId, @PathVariable Long ingredientId, Model model) {
    model.addAttribute("ingredient", ingredientServices.findByRecipeIdAndIngredientId(recipeId, ingredientId));
    return "recipe/ingredient/show";
  }
}
