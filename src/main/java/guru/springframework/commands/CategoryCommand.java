package guru.springframework.commands;

import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryCommand {

  private Long id;
  private String description;

  private Set<RecipeCommand> recipes = new HashSet<>();
}
