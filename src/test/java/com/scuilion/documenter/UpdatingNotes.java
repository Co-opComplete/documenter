package com.scuilion.documenter;

import static org.junit.Assert.*;

import java.io.*;
import java.nio.file.*;
import java.util.*;

import org.junit.*;
import org.junit.rules.*;

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
        Note note = new Note.NoteBuilder().key("fourth").priority(40).className( "class1").build();
        PropertyFile.update(Arrays.asList(note));
        assertEquals(4, Files.lines(actualFile.toPath()).count());
//        Properties properties = new Properties();
//        properties.load(new FileInputStream(actualFile));
//        for(String s :properties.stringPropertyNames()) {
//        	System.out.println(s);
//        }

    }
	
    private List<Note> createNotes() {
        List<Note> notes = new ArrayList<>();
        notes.add(new Note.NoteBuilder().key("first").priority(10).className( "class1").build());
        notes.add(new Note.NoteBuilder().key("second").priority(20).className( "class1").build());
        notes.add(new Note.NoteBuilder().key("third").priority(30).className( "class1").build());
        return notes;
    }

}
