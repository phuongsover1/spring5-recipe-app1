package guru.springframework.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.domain.Category;
import guru.springframework.domain.Recipe;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import guru.springframework.services.RecipeServices;

@Controller
public class HomeController {

  private final CategoryRepository categoryRepository;
  private final UnitOfMeasureRepository unitOfMeasureRepository;
  private final RecipeServices recipeServices;

  @Autowired
  public HomeController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository,
      RecipeServices recipeServices) {
    this.categoryRepository = categoryRepository;
    this.unitOfMeasureRepository = unitOfMeasureRepository;
    this.recipeServices = recipeServices;
  }

  @RequestMapping({ "", "index.html", "index" })
  public String index(Model model) {

    model.addAttribute("recipes", recipeServices.findAll());

    return "index";
  }
}
