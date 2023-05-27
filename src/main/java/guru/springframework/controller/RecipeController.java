package guru.springframework.controller;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/recipe")
@Slf4j
public class RecipeController {

  private final RecipeServices recipeServices;

  @Autowired
  public RecipeController(RecipeServices recipeServices) {
    this.recipeServices = recipeServices;
  }

  @GetMapping("/{id}/show")
  public String getRecipe(@PathVariable String id, Model model) {
    Recipe recipe = recipeServices.findById(Long.parseLong(id));
    model.addAttribute("recipe", recipe);
    return "recipe/show";
  }

  @GetMapping("/new")
  public String newRecipe(Model model) {
    model.addAttribute("recipe", new RecipeCommand());
    return "recipe/recipeform";
  }

  @GetMapping("/{id}/update")
  public String updateRecipe(@PathVariable String id, Model model) {
    model.addAttribute(
        "recipe",
        recipeServices.findCommandById(Long.valueOf(id)));
    return "recipe/recipeform";
  }

  @PostMapping(value = { "" })
  public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
    RecipeCommand savedCommand = recipeServices.saveRecipeCommand(command);
    return "redirect:/recipe/show/" + savedCommand.getId();
  }

  @GetMapping("/{id}/delete")
  public String deleteRecipe(@PathVariable String id) {
    log.debug("In deleteRecipe mapping");
    recipeServices.deleteById(Long.valueOf(id));
    return "redirect:/";
  }
}
