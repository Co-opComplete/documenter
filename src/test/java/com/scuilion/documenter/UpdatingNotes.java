package com.scuilion.documenter;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class UpdatingNotes {

	@Rule
	public TemporaryFolder testFolder = new TemporaryFolder();

	@Before
	public void recreateTempFolder() throws IOException {
		testFolder.create();
        PropertyFile.setTranslationLocation(testFolder.getRoot());	
	}

	@Test
	public void addingProperties() throws IOException {
		testFolder.newFile("message.properties");
        PropertyFile.update(createNotes());	

		File actualFile = PropertyFile.getFileName();
		assertEquals(3, Files.lines(actualFile.toPath()).count());

		PropertyFile.update(Arrays.asList(new Note("fourth", 40)));
		assertEquals(4, Files.lines(actualFile.toPath()).count());
//		Properties properties = new Properties();
//        properties.load(new FileInputStream(actualFile));
//        for(String s :properties.stringPropertyNames()) {
//        	System.out.println(s);
//        }

	}
	
	private List<Note> createNotes() {
		List<Note> notes = new ArrayList<>();
		notes.add(new Note("first", 10));
		notes.add(new Note("second", 20));
		notes.add(new Note("third", 30));
		return notes;
	}

}
