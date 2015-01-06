package com.scuilion.documenter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collections;

import javax.lang.model.SourceVersion;

import org.junit.Test;

public class ProcessorTest{
    
    @Test
    public void supportedSettings(){
        AnnotationProcessor processor = new AnnotationProcessor();
        
        assertEquals(SourceVersion.RELEASE_8, processor.getSupportedSourceVersion());
        assertEquals(Collections.singleton("com.scuilion.documenter.Document"), processor.getSupportedAnnotationTypes());
        assertEquals(Collections.<String>emptySet(), processor.getSupportedOptions());

        assertTrue(true);
    }
}
