package guru.springframework.bootstrap;

import guru.springframework.domain.Difficulty;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Notes;
import guru.springframework.domain.Recipe;
import guru.springframework.services.CategoryServices;
import guru.springframework.services.RecipeServices;
import guru.springframework.services.UnitOfMeasureServices;
import java.math.BigDecimal;
import java.util.HashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

  private final RecipeServices recipeServices;
  private final CategoryServices categoryServices;
  private final UnitOfMeasureServices unitOfMeasureServices;

  @Autowired
  public DataLoader(
      RecipeServices recipeServices,
      CategoryServices categoryServices,
      UnitOfMeasureServices unitOfMeasureServices) {
    this.recipeServices = recipeServices;
    this.categoryServices = categoryServices;
    this.unitOfMeasureServices = unitOfMeasureServices;
  }

  @Override
  public void run(String... args) throws Exception {
    // TODO Auto-generated method stub
    loadingRecipes();
  }

  private void loadingRecipes() {
    Recipe guacamole = recipeServices.createRecipe(
        "Guacamole",
        10,
        0,
        4,
        "ELISE BAUER",
        null,
        "lorem ip sum 1",
        new HashSet<>(),
        Difficulty.EASY,
        new Notes(
            "Be careful handling chilis! If using, it's best to wear food-safe gloves. If no gloves are available, wash your hands thoroughly after handling, and do not touch your eyes or the area near your eyes for several hours afterwards."),
        new HashSet<>());

    guacamole.getNotes().setRecipe(guacamole);
    guacamole
        .getCategories()
        .add(categoryServices.findByDescription("American").get());
    guacamole
        .getCategories()
        .add(categoryServices.findByDescription("Fast Food").get());

    guacamole.setUrl("https://www.hihi.com.vn");

    guacamole.addIngredient(
        new Ingredient(
            "Avocados",
            new BigDecimal(2),
            guacamole,
            unitOfMeasureServices.findByDescription("Ripe").get()));

    guacamole.addIngredient(
        new Ingredient(
            "Kosher salt",
            new BigDecimal(1.0 / 4),
            guacamole,
            unitOfMeasureServices.findByDescription("Teaspoon").get()));
    recipeServices.save(guacamole);

    Recipe grilledChicken = recipeServices.createRecipe(
        "Spicy Grilled Chicken Tacos",
        20,
        15,
        6,
        "SALLY VARGAS",
        null,
        "lorem ip sum 2",
        new HashSet<>(),
        Difficulty.MORDERATE,
        new Notes(
            "Look for ancho chile powder with the Mexican ingredients at your grocery store, on buy it online. (If you can't find ancho chili powder, you replace the ancho chili, the oregano, and the cumin with 2 1/2 tablespoons regular chili powder, though the flavor won't be quite the same.)"),
        new HashSet<>());

    grilledChicken.getNotes().setRecipe(grilledChicken);
    grilledChicken
        .getCategories()
        .add(categoryServices.findByDescription("Maxican").get());

    grilledChicken.addIngredient(
        new Ingredient(
            "Ancho chili powder",
            new BigDecimal(2),
            grilledChicken,
            unitOfMeasureServices.findByDescription("Tablespoon").get()));
    grilledChicken.addIngredient(
        new Ingredient(
            "Dried oregano",
            new BigDecimal(1),
            grilledChicken,
            unitOfMeasureServices.findByDescription("Teaspoon").get()));

    Recipe temp = recipeServices.save(grilledChicken);
  }
}
