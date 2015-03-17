package com.scuilion.documenter;

import static org.hamcrest.MatcherAssert.*;
//import static org.hamcrest.core.IsEqual.*;
import static org.hamcrest.Matchers.*;

import java.util.*;

import javax.enterprise.inject.*;
import javax.lang.model.element.*;

import org.junit.*;

@Alternative 
public class TestWriter implements Writer {

    @Override
    public void write(Map<String, Note> documents) {
        System.out.println("xxxx");
        for(String s : documents.keySet()){
            System.out.println(s);
        }
        System.out.println("xxxx");

        validateVariableElements(documents);
        validatePackageElements(documents);
        validateTypeElements(documents);
        validateExecutableElements(documents);
        
        //allPrioritiesCovered(documents);
    }

    @SuppressWarnings("unchecked")
    private void allPrioritiesCovered(Map<String, Note> documents) {
        List<Integer> expectedPriorities = new ArrayList<>();
        expectedPriorities.add(1000);
        expectedPriorities.add(2000);
        expectedPriorities.add(3000);

        expectedPriorities.add(100);
        expectedPriorities.add(111);
        expectedPriorities.add(200);
        expectedPriorities.add(300);
        expectedPriorities.add(400);
        expectedPriorities.add(500);
        expectedPriorities.add(700);
        expectedPriorities.add(800);
        expectedPriorities.add(350);
        expectedPriorities.add(450);
        expectedPriorities.add(550);
        expectedPriorities.add(550);


        List<Integer> actualPriorities = new ArrayList<>();
        for(Note note: documents.values()) {
            actualPriorities.add(note.getPriority());
        }
        assertThat(expectedPriorities, containsInAnyOrder(actualPriorities));
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
        
        String key = "projectRoot.main.java.com.scuilion.SingleMethod.aSingleMethod.a.single.method";
        assertThat(documents, hasKey(key));
        Note note = documents.get(key);
        assertThat(note.getClassName(), is("projectRoot.main.java.com.scuilion.SingleMethod")); 
        assertThat(note.getElementKind(), is(ElementKind.METHOD)); 
        assertThat(note.getPriority(), is(111)); 

        key = "projectRoot.main.java.com.scuilion.ProcessorTestClass.someMethod.some.method";
        assertThat(documents, hasKey(key));
        note = documents.get(key);
        assertThat(note.getClassName(), is("projectRoot.main.java.com.scuilion.ProcessorTestClass")); 
        assertThat(note.getElementKind(), is(ElementKind.METHOD)); 
        assertThat(note.getPriority(), is(400)); 

        key = "projectRoot.main.java.com.scuilion.ProcessorTestClass.anotherMethod.another.method";
        assertThat(documents, hasKey(key));
        note = documents.get(key);
        assertThat(note.getClassName(), is("projectRoot.main.java.com.scuilion.ProcessorTestClass")); 
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
        String key = "projectRoot.main.java.com.scuilion.ProcessorTestClass.with.type.use";
        Note note = documents.get(key);
        assertThat(note.getClassName(), is("projectRoot.main.java.com.scuilion.ProcessorTestClass")); 
        assertThat(note.getElementKind(), is(ElementKind.PARAMETER)); 
        assertThat(note.getPriority(), is(700)); 
    }

    private void constructorParameterKind(Map<String, Note> documents) {
        String key = "projectRoot.main.java.com.scuilion.ProcessorTestClass.param1.constructor.parameter";
        assertThat(documents, hasKey(key));
        Note note = documents.get(key);
        assertThat(note.getClassName(), is("projectRoot.main.java.com.scuilion.ProcessorTestClass")); 
        assertThat(note.getElementKind(), is(ElementKind.PARAMETER)); 
        assertThat(note.getPriority(), is(350)); 
    }

    private void annotationKind(Map<String, Note> documents) {
        String key = "projectRoot.main.java.com.scuilion.GenericAnnotation.GenericAnnotation.annotation.type";
        assertThat(documents, hasKey(key));
        Note note = documents.get(key);
        assertThat(note.getClassName(), is("projectRoot.main.java.com.scuilion.GenericAnnotation")); 
        assertThat(note.getElementKind(), is(ElementKind.ANNOTATION_TYPE)); 
        assertThat(note.getPriority(), is(3000)); 
    }

    private void packageKind(Map<String, Note> documents) {
        String key = "projectRoot.main.java.com.scuilion.package.info";
        assertThat(documents, hasKey(key));
        Note note = documents.get(key);
        //assertThat(note.getClassName(), is("")); 
        assertThat(note.getElementKind(), is(ElementKind.PACKAGE)); 
        assertThat(note.getPriority(), is(2000)); 
    }

    private void enummeratedConstantKind(Map<String, Note> documents) {
        String key = "projectRoot.main.java.com.scuilion.package.info";
        assertThat(documents, hasKey("foo.enum.constant"));
        Note note = documents.get("foo.enum.constant");
        assertThat(note.getElementKind(), is(ElementKind.ENUM_CONSTANT)); 
        assertThat(note.getPriority(), is(1000)); 
    }

    private void instanceVariableKind(Map<String, Note> documents) {
        //aka: field, instanceVariable, member variables
        String key = "projectRoot.main.java.com.scuilion.ProcessorTestClass.really.instance.variable";
        assertThat(documents, hasKey(key));
        Note note = documents.get(key);
        assertThat(note.getClassName(), is("projectRoot.main.java.com.scuilion.ProcessorTestClass")); 
        assertThat(note.getElementKind(), is(ElementKind.FIELD)); 
        assertThat(note.getPriority(), is(300)); 
    }

    @Test
    public void enummeratedKind(Map<String, Note> documents){
        String key = "projectRoot.main.java.com.scuilion.Day.Day.enum";
        assertThat(documents, hasKey(key));
        Note note = documents.get(key);
        assertThat(note.getClassName(), is("projectRoot.main.java.com.scuilion.Day")); 
        assertThat(note.getElementKind(), is(ElementKind.ENUM)); 
        assertThat(note.getPriority(), is(1000)); 
    }
}
