package guru.springframework.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NotesCommand {

  private Long id;

  private RecipeCommand recipe;

  private String recipeNotes;
}
