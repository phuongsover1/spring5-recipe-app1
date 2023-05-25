package guru.springframework.converters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import guru.springframework.commands.NotesCommand;
import guru.springframework.domain.Notes;

public class NotesCommandToNotesTest {
  private NotesCommandToNotes nCommandToNotes;

  @BeforeEach
  void setUp() {
    nCommandToNotes = new NotesCommandToNotes();
  }

  @Test
  void testNullParameter() {
    assertNull(nCommandToNotes.convert(null));
  }

  @Test
  void testConvert() {
    Long id = 1L;
    String description = "Notes Description";
    NotesCommand notesCommand = new NotesCommand();
    notesCommand.setId(id);
    notesCommand.setRecipeNotes(description);

    Notes notes = nCommandToNotes.convert(notesCommand);

    assertNotNull(notes);
    assertEquals(id, notes.getId());
    assertEquals(description, notes.getRecipeNotes());
  }
}
