package guru.springframework.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.domain.Category;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;

@Controller
public class HomeController {

  private final CategoryRepository categoryRepository;
  private final UnitOfMeasureRepository unitOfMeasureRepository;

  @Autowired
  public HomeController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
    this.categoryRepository = categoryRepository;
    this.unitOfMeasureRepository = unitOfMeasureRepository;
  }

  @RequestMapping({ "", "index.html", "index" })
  public String index(Model model) {

    Optional<Category> cOptional = categoryRepository.findByDescription("Italian");
    Optional<UnitOfMeasure> uOptional = unitOfMeasureRepository.findByDescription("Cup");
    model.addAttribute("cId", cOptional.get().getId());
    model.addAttribute("uId", uOptional.get().getId());
    return "index";
  }
}
