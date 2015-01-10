package com.scuilion.documenter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class PropertyFileTest {

	@Rule
	public TemporaryFolder testFolder = new TemporaryFolder();

	@Test
	public void createFileTest() throws IOException {
		File propertyFile = testFolder.newFile("application.properties");
		propertyFile.createNewFile();
		List<Note> documents = createNotes();
		PropertyFile.update(documents, propertyFile);
	}

	private List<Note> createNotes() {
		List<Note> notes = new ArrayList<>();
		notes.add(new Note("first", 10));
		notes.add(new Note("second", 20));
		notes.add(new Note("third", 30));
		return notes;
	}

}
