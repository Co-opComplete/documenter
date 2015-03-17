package com.scuilion.documenter;

import static org.junit.Assert.*;

import java.io.*;
import java.util.*;

import org.junit.*;
import org.junit.rules.*;

// import com.scuilion.documenter.Document;

public class CheckingAvailableFileTest {

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

//     @Document(key="test", priority=999)
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
        notes.add(new Note.NoteBuilder().key("first").priority(10).className( "class1").build());
        notes.add(new Note.NoteBuilder().key("second").priority(20).className( "class1").build());
        notes.add(new Note.NoteBuilder().key("third").priority(30).className( "class1").build());
        return notes;
    }
}
