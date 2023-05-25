package guru.springframework.converters;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.domain.Ingredient;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientCommandToIngredient
    implements Converter<IngredientCommand, Ingredient> {

  private final UnitOfMeasureCommandToUnitOfMeasure uomConverter;

  @Autowired
  public IngredientCommandToIngredient(
      UnitOfMeasureCommandToUnitOfMeasure uomConverter) {
    this.uomConverter = uomConverter;
  }

  @Synchronized
  @Override
  @Nullable
  public Ingredient convert(IngredientCommand source) {
    if (source == null)
      return null;

    final Ingredient ingredient = new Ingredient();
    ingredient.setId(source.getId());
    ingredient.setDescription(source.getDescription());
    ingredient.setAmount(source.getAmount());
    ingredient.setUom(uomConverter.convert(source.getUom()));
    return ingredient;
  }
}
