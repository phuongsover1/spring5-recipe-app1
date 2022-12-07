package guru.springframework.converters;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.domain.Ingredient;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientToIngredientCommand
  implements Converter<Ingredient, IngredientCommand> {

  private final UnitOfMeasureToUnitOfMeasureCommand uomConverter;

  @Autowired
  public IngredientToIngredientCommand(
    UnitOfMeasureToUnitOfMeasureCommand uomConverter
  ) {
    this.uomConverter = uomConverter;
  }

  @Synchronized
  @Override
  @Nullable
  public IngredientCommand convert(Ingredient source) {
    if (source == null) return null;
    final IngredientCommand iCommand = new IngredientCommand();
    iCommand.setId(source.getId());
    iCommand.setDescription(source.getDescription());
    iCommand.setAmount(source.getAmount());
    iCommand.setUom(uomConverter.convert(source.getUom()));
    return iCommand;
  }
}
