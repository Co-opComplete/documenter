package com.scuilion.documenter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasKey;
import static org.junit.Assert.assertEquals;

import java.util.Map;

import javax.enterprise.inject.Alternative;
import javax.lang.model.element.ElementKind;

import org.junit.Test;

@Alternative 
public class TestWriter implements Writer {

    @Override
    public void write(Map<String, Note> documents) {
        instanceVariable(documents);
    }

    @Test
    public void instanceVariable(Map<String, Note> documents){
        assertThat(documents, hasKey("really.instance.variable"));
        Note note = documents.get("really.instance.variable");
        assertEquals(note.getKey(), "really.instance.variable");
        assertEquals(note.getElementKind(), ElementKind.FIELD);
        assertEquals(note.getPriority(), 300);
    }
}
