package com.scuilion.documenter;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.element.VariableElement;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Spy;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;

public class ScannerTest{
    
    //visitExecutable, visitPackage, visitType, visitTypeParameter, visitVariable
    @Mock private ExecutableElement executableElement;
    @Mock private PackageElement packageElement;
    @Mock private TypeElement typeElement;
    @Mock private TypeParameterElement typeParameterElement;
    @Mock private VariableElement variableElement;
    @Spy private static Scanner scanner;// = new Scanner();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void visitVariableTest(){
        resetupMockDocument(variableElement, 50);
        
        List<String> documents = new ArrayList<>();
        scanner.visitVariable(variableElement, documents);

        verify(scanner).addDocument(any(Element.class), anyListOf(String.class));
        assertTrue(documents.get(0).equals("50"));
    }

    @Test
    public void visitExecutableTest(){
        resetupMockDocument(executableElement, 40);
        when(executableElement.getEnclosingElement()).thenReturn(executableElement);

        List<String> documents = new ArrayList<>();
        scanner.visitExecutable(executableElement, documents);

        verify(scanner).addDocument(any(Element.class), anyListOf(String.class));
        assertTrue(documents.get(0).equals("40"));
    }

    @Test
    public void visitPackageElementTest(){
        resetupMockDocument(packageElement, 30);
        
        List<String> documents = new ArrayList<>();
        scanner.visitPackage(packageElement, documents);

        verify(scanner).addDocument(any(Element.class), anyListOf(String.class));
        assertTrue(documents.get(0).equals("30"));
    }

    @Test
    public void visitTypeTest(){
        resetupMockDocument(typeElement, 10);
        
        List<String> documents = new ArrayList<>();
        scanner.visitType(typeElement, documents);

        verify(scanner).addDocument(any(Element.class), anyListOf(String.class));
        assertTrue(documents.get(0).equals("10"));
    }
    
    @Test
    public void visitTypeParameterTest(){
        resetupMockDocument(typeParameterElement, 20);

        List<String> documents = new ArrayList<>();
        scanner.visitTypeParameter(typeParameterElement, documents);

        verify(scanner).addDocument(any(Element.class), anyListOf(String.class));
        assertTrue(documents.get(0).equals("20"));
    }

    private void resetupMockDocument(Element e, int priority) {
        Document document = mock(Document.class);
        when(document.priority()).thenReturn(priority);

        when(e.getAnnotation(Document.class)).thenReturn(document);
    }

}

