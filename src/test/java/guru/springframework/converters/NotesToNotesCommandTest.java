package guru.springframework.converters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import guru.springframework.commands.NotesCommand;
import guru.springframework.domain.Notes;

public class NotesToNotesCommandTest {

  private NotesToNotesCommand nToNotesCommand;

  @BeforeEach
  void setUp() {
    nToNotesCommand = new NotesToNotesCommand();
  }

  @Test
  void testNullParameter() {
    assertNull(nToNotesCommand.convert(null));
  }

  @Test
  void testConvert() {
    Notes notes = new Notes();
    notes.setId(1L);
    notes.setRecipeNotes("sadf");

    NotesCommand nCommand = nToNotesCommand.convert(notes);

    assertNotNull(nCommand);
    assertEquals(notes.getId(), nCommand.getId());
    assertEquals(notes.getRecipeNotes(), nCommand.getRecipeNotes());

  }
}
