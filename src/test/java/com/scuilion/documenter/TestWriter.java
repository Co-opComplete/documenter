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
        validateVariableElements(documents);
        validatePackageElements(documents);
        validateTypeElements(documents);
    }

    private void validateTypeElements(Map<String, Note> documents) {
        //javax.lang.model.element.TypeElement
        annotationKind(documents);
        enummeratedKind(documents);
    }

    private void validatePackageElements(Map<String, Note> documents) {
        //javax.lang.model.element.PackageElement, only one 
        packageKind(documents);
    }

    private void validateVariableElements(Map<String, Note> documents) {
        //javax.lang.model.element.VariableElement
        instanceVariableKind(documents);
        methodParameterKind(documents);
        constructorParameterKind(documents);
//        resourceVariableKind(documents);
//        exceptionVariableKind(documents);
        //enummeratedConstantKind(documents);
        //methodKind(documents);
    }

    private void exceptionVariableKind(Map<String, Note> documents) {
        assertThat(documents, hasKey("e.exception.variable"));
        Note note = documents.get("e.exception.variable");
        assertEquals(note.getElementKind(), ElementKind.PARAMETER);
        assertEquals(note.getPriority(), 550);
    }

    private void resourceVariableKind(Map<String, Note> documents) {
        assertThat(documents, hasKey("r.resource.variable"));
        Note note = documents.get("r.resource.variable");
        assertEquals(note.getElementKind(), ElementKind.PARAMETER);
        assertEquals(note.getPriority(), 450);
    }

    private void methodParameterKind(Map<String, Note> documents) {
        assertThat(documents, hasKey("with.type.use"));
        Note note = documents.get("with.type.use");
        assertEquals(note.getElementKind(), ElementKind.PARAMETER);
        assertEquals(note.getPriority(), 700);
    }

    private void constructorParameterKind(Map<String, Note> documents) {
        assertThat(documents, hasKey("parm1.constructor.parameter"));
        Note note = documents.get("parm1.constructor.parameter");
        assertEquals(note.getElementKind(), ElementKind.PARAMETER);
        assertEquals(note.getPriority(), 350);
    }

    private void annotationKind(Map<String, Note> documents) {
        assertThat(documents, hasKey("projectRoot.main.java.com.scuilion.GenericAnnotation.annotation.type"));
        Note note = documents.get("projectRoot.main.java.com.scuilion.GenericAnnotation.annotation.type");
        assertEquals(note.getElementKind(), ElementKind.ANNOTATION_TYPE);
        assertEquals(note.getPriority(), 3000);
    }

    private void packageKind(Map<String, Note> documents) {
        assertThat(documents, hasKey("projectRoot.main.java.com.scuilion.package.info"));
        Note note = documents.get("projectRoot.main.java.com.scuilion.package.info");
        assertEquals(note.getElementKind(), ElementKind.PACKAGE);
        assertEquals(note.getPriority(), 2000);
    }

    private void methodKind(Map<String, Note> documents) {
        assertThat(documents, hasKey("really.somemethod"));
        Note note = documents.get("really.somemethod");
        assertEquals(note.getElementKind(), ElementKind.METHOD);
        assertEquals(note.getPriority(), 400);
    }

    @Test
    private void enummeratedConstantKind(Map<String, Note> documents) {
        assertThat(documents, hasKey("foo.enum.constant"));
        Note note = documents.get("foo.enum.constant");
        assertEquals(note.getElementKind(), ElementKind.ENUM_CONSTANT);
        assertEquals(note.getPriority(), 1000);
        
    }

    @Test
    private void instanceVariableKind(Map<String, Note> documents) {
        //aka: field, instanceVariable, member variables
        assertThat(documents, hasKey("really.instance.variable"));
        Note note = documents.get("really.instance.variable");
        assertEquals(note.getElementKind(), ElementKind.FIELD);
        assertEquals(note.getPriority(), 300);
    }

    @Test
    public void enummeratedKind(Map<String, Note> documents){
        assertThat(documents, hasKey("projectRoot.main.java.com.scuilion.Day.enum"));
        Note note = documents.get("projectRoot.main.java.com.scuilion.Day.enum");
        assertEquals(note.getElementKind(), ElementKind.ENUM);
        assertEquals(note.getPriority(), 1000);
    }
}
