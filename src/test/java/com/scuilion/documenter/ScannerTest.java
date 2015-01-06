package com.scuilion.documenter;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ScannerTest{
    
    @Mock private PackageElement packageElement;
    @Mock private TypeElement typeElement;
    @Mock private TypeParameterElement typeParameterElement;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void visitTypeTest(){
        resetupMockDocument(typeElement, 10);
        
        List<String> documents = new ArrayList<>();
        Scanner scanner = new Scanner();
        scanner.visitType(typeElement, documents);

        assertTrue(documents.get(0).equals("10"));
    }
    
    @Test
    public void visitTypeParameterTest(){
        resetupMockDocument(typeParameterElement, 20);

        List<String> documents = new ArrayList<>();
        Scanner scanner = new Scanner();
        scanner.visitTypeParameter(typeParameterElement, documents);

        assertTrue(documents.get(0).equals("20"));
    }

    private void resetupMockDocument(Element e, int priority) {
        Document document = mock(Document.class);
        when(document.priority()).thenReturn(priority);

        when(e.getAnnotation(Document.class)).thenReturn(document);
    }

}

