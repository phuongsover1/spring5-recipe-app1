package guru.springframework.bootstrap;

import java.math.BigDecimal;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import guru.springframework.domain.Difficulty;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Notes;
import guru.springframework.domain.Recipe;
import guru.springframework.services.CategoryServices;
import guru.springframework.services.RecipeServices;
import guru.springframework.services.UnitOfMeasureServices;

public class DataLoader implements CommandLineRunner {
  private final RecipeServices recipeServices;
  private final CategoryServices categoryServices;
  private final UnitOfMeasureServices unitOfMeasureServices;

  @Autowired
  public DataLoader(RecipeServices recipeServices, CategoryServices categoryServices,
      UnitOfMeasureServices unitOfMeasureServices) {
    this.recipeServices = recipeServices;
    this.categoryServices = categoryServices;
    this.unitOfMeasureServices = unitOfMeasureServices;
  }

  @Override
  public void run(String... args) throws Exception {
    // TODO Auto-generated method stub

  }

  private void loadingRecipes() {
    Recipe guacamole = recipeServices.createRecipe(
        "Guacamole",
        10,
        0,
        4, "ELISE BAUER", null, null,
        new HashSet<>(), Difficulty.EASY,
        new Notes(
            "Be careful handling chilis! If using, it's best to wear food-safe gloves. If no gloves are available, wash your hands thoroughly after handling, and do not touch your eyes or the area near your eyes for several hours afterwards."),
        new HashSet<>());

    guacamole.getCategories().add(categoryServices.findByDescription("American").get());

    guacamole.getIngredients().add(new Ingredient("Avocados", new BigDecimal(2), guacamole,
        unitOfMeasureServices.findByDescription("Ripe").get()));

    recipeServices.save(guacamole);

    Recipe grilledChicken = recipeServices.createRecipe("Spicy Grilled Chicken Tacos", 20, 15, 6, "SALLY VARGAS", null,
        null, new HashSet<>(), Difficulty.MORDERATE,
        new Notes(
            "Look for ancho chile powder with the Mexican ingredients at your grocery store, on buy it online. (If you can't find ancho chili powder, you replace the ancho chili, the oregano, and the cumin with 2 1/2 tablespoons regular chili powder, though the flavor won't be quite the same.)"),
        new HashSet<>());
  }

}
