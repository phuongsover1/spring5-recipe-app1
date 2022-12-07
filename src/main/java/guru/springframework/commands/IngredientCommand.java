package guru.springframework.commands;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class IngredientCommand {

  private Long id;
  private String description;
  private BigDecimal amount;
  private RecipeCommand recipe;
  private UnitOfMeasureCommand uom;
}
