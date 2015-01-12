package com.scuilion.documenter;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class CheckingAvailableFileTest {

	@Rule
	public TemporaryFolder testFolder = new TemporaryFolder();

	@Before
	public void recreateTempFolder() throws IOException {
		testFolder.create();
        PropertyFile.setTranslationLocation(testFolder.getRoot());	
	}

	@Test
	public void onlyGetMesages() throws IOException {
		testFolder.newFile("somethinelse.properties");
		assertEquals(1, PropertyFile.getListOfProperties().size());
		assertEquals("message.properties", PropertyFile.getListOfProperties().get(0).getName());
	}

	@Test
	public void ifDefaultDoesnotExist() throws IOException {
		assertEquals(1, PropertyFile.getListOfProperties().size());
		assertEquals("message.properties", PropertyFile.getListOfProperties().get(0).getName());
	}
	@Test
	public void gettingProperityFiles() throws IOException {
		testFolder.newFile("message.properties");
		testFolder.newFile("message_en_US.properties");
		testFolder.newFile("message_fr_CA.properties");
		assertEquals(3, PropertyFile.getListOfProperties().size());
	}

	private List<Note> createNotes() {
		List<Note> notes = new ArrayList<>();
		notes.add(new Note("first", 10));
		notes.add(new Note("second", 20));
		notes.add(new Note("third", 30));
		return notes;
	}

}
