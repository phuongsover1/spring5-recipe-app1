package guru.springframework.converters;

import guru.springframework.commands.NotesCommand;
import guru.springframework.domain.Notes;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NotesToNotesCommand implements Converter<Notes, NotesCommand> {

  @Synchronized
  @Override
  @Nullable
  public NotesCommand convert(Notes source) {
    if (source == null) return null;
    final NotesCommand nCommand = new NotesCommand();
    nCommand.setId(source.getId());
    nCommand.setRecipeNotes(source.getRecipeNotes());
    return nCommand;
  }
}
