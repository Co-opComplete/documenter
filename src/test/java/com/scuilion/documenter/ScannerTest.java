package com.scuilion.documenter;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
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
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

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
        resetupMockDocument(variableElement, 50, "variable");
        
        List<Note> documents = new ArrayList<>();
        scanner.visitVariable(variableElement, documents);

        verify(scanner).addDocument(any(Element.class), anyListOf(Note.class));
        assertEquals(50, documents.get(0).getPriority());
        assertEquals("variable", documents.get(0).getKey());
    }

    @Test
    public void visitExecutableTest(){
        resetupMockDocument(executableElement, 40, "executable");
        when(executableElement.getEnclosingElement()).thenReturn(executableElement);

        List<Note> documents = new ArrayList<>();
        scanner.visitExecutable(executableElement, documents);

        verify(scanner).addDocument(any(Element.class), anyListOf(Note.class));
        assertEquals(40, documents.get(0).getPriority());
        assertEquals("executable", documents.get(0).getKey());
    }

    @Test
    public void visitPackageElementTest(){
        resetupMockDocument(packageElement, 30, "package");
        
        List<Note> documents = new ArrayList<>();
        scanner.visitPackage(packageElement, documents);

        verify(scanner).addDocument(any(Element.class), anyListOf(Note.class));
        assertEquals(30, documents.get(0).getPriority());
        assertEquals("package", documents.get(0).getKey());
    }

    @Test
    public void visitTypeTest(){
        resetupMockDocument(typeElement, 10, "type");
        
        List<Note> documents = new ArrayList<>();
        scanner.visitType(typeElement, documents);

        verify(scanner).addDocument(any(Element.class), anyListOf(Note.class));
        assertEquals(10, documents.get(0).getPriority());
        assertEquals("type", documents.get(0).getKey());
    }
    
    @Test
    public void visitTypeParameterTest(){
        resetupMockDocument(typeParameterElement, 20, "parameter");

        List<Note> documents = new ArrayList<>();
        scanner.visitTypeParameter(typeParameterElement, documents);

        verify(scanner).addDocument(any(Element.class), anyListOf(Note.class));
        assertEquals(20, documents.get(0).getPriority());
        assertEquals("parameter", documents.get(0).getKey());
    }

    private void resetupMockDocument(Element e, int priority, String key) {
        Document document = mock(Document.class);
        when(document.priority()).thenReturn(priority);
        when(document.key()).thenReturn(key);

        when(e.getAnnotation(Document.class)).thenReturn(document);
    }

}

