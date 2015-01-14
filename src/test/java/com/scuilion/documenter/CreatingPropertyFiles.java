package com.scuilion.documenter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

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
		notes.add(new Note("first", 10, "class1"));
		notes.add(new Note("second", 20, "class1"));
		notes.add(new Note("third", 30, "class1"));
		return notes;
	}

}
