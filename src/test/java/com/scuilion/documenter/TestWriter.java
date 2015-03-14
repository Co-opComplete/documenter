package com.scuilion.documenter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

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
        assertThat(documents.size(), is(not(0)));
        assertThat(documents, hasKey("aSingleMethod().a.single.method"));
        Note note = documents.get("aSingleMethod().a.single.method");
        assertThat(note.getElementKind(), is(ElementKind.METHOD)); 
        assertThat(note.getPriority(), is(111)); 

        assertThat(documents, hasKey("someMethod().some.method"));
        note = documents.get("someMethod().some.method");
        assertThat(note.getElementKind(), is(ElementKind.METHOD)); 
        assertThat(note.getPriority(), is(400)); 

        assertThat(documents, hasKey("anotherMethod().another.method"));
        note = documents.get("anotherMethod().another.method");
        assertThat(note.getElementKind(), is(ElementKind.METHOD)); 
        assertThat(note.getPriority(), is(500)); 
    }

    private void exceptionVariableKind(Map<String, Note> documents) {
        assertThat(documents, hasKey("e.exception.variable"));
        Note note = documents.get("e.exception.variable");
        assertThat(note.getElementKind(), is(ElementKind.PARAMETER)); 
        assertThat(note.getPriority(), is(550)); 
    }

    private void resourceVariableKind(Map<String, Note> documents) {
        assertThat(documents, hasKey("r.resource.variable"));
        Note note = documents.get("r.resource.variable");
        assertThat(note.getElementKind(), is(ElementKind.PARAMETER)); 
        assertThat(note.getPriority(), is(450)); 
    }

    private void methodParameterKind(Map<String, Note> documents) {
        assertThat(documents, hasKey("with.type.use"));
        Note note = documents.get("with.type.use");
        assertThat(note.getElementKind(), is(ElementKind.PARAMETER)); 
        assertThat(note.getPriority(), is(700)); 
    }

    private void constructorParameterKind(Map<String, Note> documents) {
        assertThat(documents, hasKey("parm1.constructor.parameter"));
        Note note = documents.get("parm1.constructor.parameter");
        assertThat(note.getElementKind(), is(ElementKind.PARAMETER)); 
        assertThat(note.getPriority(), is(350)); 
    }

    private void annotationKind(Map<String, Note> documents) {
        assertThat(documents, hasKey("projectRoot.main.java.com.scuilion.GenericAnnotation.annotation.type"));
        Note note = documents.get("projectRoot.main.java.com.scuilion.GenericAnnotation.annotation.type");
        assertThat(note.getElementKind(), is(ElementKind.ANNOTATION_TYPE)); 
        assertThat(note.getPriority(), is(3000)); 
    }

    private void packageKind(Map<String, Note> documents) {
        assertThat(documents, hasKey("projectRoot.main.java.com.scuilion.package.info"));
        Note note = documents.get("projectRoot.main.java.com.scuilion.package.info");
        assertThat(note.getElementKind(), is(ElementKind.PACKAGE)); 
        assertThat(note.getPriority(), is(2000)); 
    }

    @Test
    private void enummeratedConstantKind(Map<String, Note> documents) {
        assertThat(documents, hasKey("foo.enum.constant"));
        Note note = documents.get("foo.enum.constant");
        assertThat(note.getElementKind(), is(ElementKind.ENUM_CONSTANT)); 
        assertThat(note.getPriority(), is(1000)); 
    }

    @Test
    private void instanceVariableKind(Map<String, Note> documents) {
        //aka: field, instanceVariable, member variables
        assertThat(documents, hasKey("really.instance.variable"));
        Note note = documents.get("really.instance.variable");
        assertThat(note.getElementKind(), is(ElementKind.FIELD)); 
        assertThat(note.getPriority(), is(300)); 
    }

    @Test
    public void enummeratedKind(Map<String, Note> documents){
        assertThat(documents, hasKey("projectRoot.main.java.com.scuilion.Day.enum"));
        Note note = documents.get("projectRoot.main.java.com.scuilion.Day.enum");
        assertThat(note.getElementKind(), is(ElementKind.ENUM)); 
        assertThat(note.getPriority(), is(1000)); 
    }
}
