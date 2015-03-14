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
        validateExecutableElements(documents);
    }

    private void validateExecutableElements(Map<String, Note> documents) {
        methodKind(documents);
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
    }

    private void methodKind(Map<String, Note> documents) {
        assertThat(documents, hasKey("projectRoot.main.java.com.scuilion.ProcessorTestClass.class.someMethod"));
        Note note = documents.get("projectRoot.main.java.com.scuilion.ProcessorTestClass.class.someMethod");
        assertEquals(ElementKind.CLASS, note.getElementKind());
        assertEquals(400, note.getPriority());
        
        assertThat(documents, hasKey("projectRoot.main.java.com.scuilion.ProcessorTestClass.class.anotherMethod"));
        note = documents.get("projectRoot.main.java.com.scuilion.ProcessorTestClass.class.anotherMethod");
        assertEquals(ElementKind.CLASS, note.getElementKind());
        assertEquals(500, note.getPriority());
    }

    private void exceptionVariableKind(Map<String, Note> documents) {
        assertThat(documents, hasKey("e.exception.variable"));
        Note note = documents.get("e.exception.variable");
        assertEquals(ElementKind.PARAMETER, note.getElementKind());
        assertEquals(550, note.getPriority());
    }

    private void resourceVariableKind(Map<String, Note> documents) {
        assertThat(documents, hasKey("r.resource.variable"));
        Note note = documents.get("r.resource.variable");
        assertEquals(ElementKind.PARAMETER, note.getElementKind());
        assertEquals(450, note.getPriority());
    }

    private void methodParameterKind(Map<String, Note> documents) {
        assertThat(documents, hasKey("with.type.use"));
        Note note = documents.get("with.type.use");
        assertEquals(ElementKind.PARAMETER, note.getElementKind());
        assertEquals(700, note.getPriority());
    }

    private void constructorParameterKind(Map<String, Note> documents) {
        assertThat(documents, hasKey("parm1.constructor.parameter"));
        Note note = documents.get("parm1.constructor.parameter");
        assertEquals(ElementKind.PARAMETER, note.getElementKind());
        assertEquals(350, note.getPriority());
    }

    private void annotationKind(Map<String, Note> documents) {
        assertThat(documents, hasKey("projectRoot.main.java.com.scuilion.GenericAnnotation.annotation.type"));
        Note note = documents.get("projectRoot.main.java.com.scuilion.GenericAnnotation.annotation.type");
        assertEquals(ElementKind.ANNOTATION_TYPE, note.getElementKind());
        assertEquals(3000, note.getPriority());
    }

    private void packageKind(Map<String, Note> documents) {
        assertThat(documents, hasKey("projectRoot.main.java.com.scuilion.package.info"));
        Note note = documents.get("projectRoot.main.java.com.scuilion.package.info");
        assertEquals(note.getElementKind(), ElementKind.PACKAGE);
        assertEquals(2000, note.getPriority());
    }

    @Test
    private void enummeratedConstantKind(Map<String, Note> documents) {
        assertThat(documents, hasKey("foo.enum.constant"));
        Note note = documents.get("foo.enum.constant");
        assertEquals(ElementKind.ENUM_CONSTANT, note.getElementKind());
        assertEquals(1000, note.getPriority());
        
    }

    @Test
    private void instanceVariableKind(Map<String, Note> documents) {
        //aka: field, instanceVariable, member variables
        assertThat(documents, hasKey("really.instance.variable"));
        Note note = documents.get("really.instance.variable");
        assertEquals(ElementKind.FIELD, note.getElementKind());
        assertEquals(300, note.getPriority());
    }

    @Test
    public void enummeratedKind(Map<String, Note> documents){
        assertThat(documents, hasKey("projectRoot.main.java.com.scuilion.Day.enum"));
        Note note = documents.get("projectRoot.main.java.com.scuilion.Day.enum");
        assertEquals(ElementKind.ENUM, note.getElementKind());
        assertEquals(1000, note.getPriority());
    }
}
