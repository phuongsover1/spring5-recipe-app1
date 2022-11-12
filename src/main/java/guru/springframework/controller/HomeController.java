package guru.springframework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.services.RecipeServices;

@Controller
public class HomeController {

  private final RecipeServices recipeServices;

  @Autowired
  public HomeController(
      RecipeServices recipeServices) {
    this.recipeServices = recipeServices;
  }

  @RequestMapping({ "", "index.html", "index" })
  public String index(Model model) {

    model.addAttribute("recipes", recipeServices.findAll());

    return "index";
  }
}
