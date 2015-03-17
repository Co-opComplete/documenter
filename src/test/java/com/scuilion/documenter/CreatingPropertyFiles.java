package com.scuilion.documenter;

import static org.junit.Assert.*;

import java.io.*;
import java.nio.file.*;
import java.util.*;

import org.junit.*;
import org.junit.rules.*;

public class CreatingPropertyFiles {

	@Rule
	public TemporaryFolder testFolder = new TemporaryFolder();

	@Before
	public void recreateTempFolder() throws IOException {
		testFolder.create();
        PropertyFile.setTranslationLocation(testFolder.getRoot());	
	}

	@Test
	public void noTranslations() throws IOException {
		File actualFile = PropertyFile.getFileName();
		assertFalse(actualFile.exists());
	}
	
	@Test
	public void handlingMultipleFiles() throws IOException {
		testFolder.newFile("message.properties");
		testFolder.newFile("message_en_CA.properties");
        PropertyFile.update(createNotes());	

		File actualFile = PropertyFile.getFileName();
		File actualFileenUS = PropertyFile.getFileName(Locale.CANADA);
		assertEquals(3, Files.lines(actualFile.toPath()).count());
		assertEquals(3, Files.lines(actualFileenUS.toPath()).count());
	}
	
	@Test
	public void createNew() throws IOException {
        PropertyFile.update(createNotes());	
		File actualFile = PropertyFile.getFileName();
		assertEquals(3, Files.lines(actualFile.toPath()).count());
	}

	private List<Note> createNotes() {
		List<Note> notes = new ArrayList<>();
        notes.add(new Note.NoteBuilder().key("first").priority(10).className( "class1").build());
        notes.add(new Note.NoteBuilder().key("second").priority(20).className( "class1").build());
        notes.add(new Note.NoteBuilder().key("third").priority(30).className( "class1").build());
		return notes;
	}

}
