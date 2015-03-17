package com.scuilion.documenter;

import java.util.*;
import java.util.regex.*;

import javax.lang.model.element.*;
import javax.lang.model.util.*;

public class Scanner extends ElementScanner8<HashMap<String, Note>, Map<String, Note>> {
    @Override
    public HashMap<String, Note> visitPackage(PackageElement e, Map<String, Note> p) {
        addDocument(e, p);
        return super.visitPackage(e, p);
    }

    @Override
    public HashMap<String, Note> visitTypeParameter(TypeParameterElement e, Map<String, Note> p) {
        addDocument(e, p);
        return super.visitTypeParameter(e, p);
    }

    @Override
    public HashMap<String, Note> visitType(TypeElement e, Map<String, Note> p) {
        addDocument(e, p);
        return super.visitType(e, p);
    }
    @Override
    public HashMap<String, Note> visitUnknown(Element e, Map<String, Note> p) {
        addDocument(e, p);
        return super.visitUnknown(e, p);
    }

    @Override
    public HashMap<String, Note> visitExecutable(ExecutableElement e, Map<String, Note> p) {
        Document[] docs = e.getAnnotationsByType(com.scuilion.documenter.Document.class);
        addDocument(docs, e, p); 
        
        if(e.getKind().equals(ElementKind.METHOD)){
            for(Element parameterElement : e.getParameters()){
                addDocument(parameterElement, p); 
            }
        }

        return super.visitExecutable(e, p);
    }

    @Override
    public HashMap<String, Note> visitVariable(VariableElement e, Map<String, Note> p) {
        addDocument(e, p);
        return super.visitVariable(e, p);
    }

    protected void addDocument(Element e, Map<String, Note> p) {
        addDocument(e, p, "");
    }

    protected void addDocument(Document[] documents, Element e, Map<String, Note> p) {
        for (int i = 0; i < documents.length; i++) {
            addDocument(documents[i], e, p);
        }
    }
    protected void addDocument(Document document, Element e, Map<String, Note> p) {
        if (document != null) {
            int priority = document.priority();
            keysCannontHaveSpaces(document.key());
            String shortKey = document.key();
            String elementName = getElementName(e);

            String className = getClassName(e);
            String fullKey = className + elementName + "." + shortKey;
            Note note = new Note.NoteBuilder().key(fullKey).priority(priority)
                    .className(className).elementKind(e.getKind()).build();
            p.put(fullKey, note);
        }
    }

    private String getElementName(Element e) {
        String elementName ="";
        if(!e.getKind().equals(ElementKind.PACKAGE)){
            elementName = "." + e.getSimpleName().toString();
        }
        return elementName;
    }

    protected void addDocument(Element e, Map<String, Note> p, String extendedName) {
        Document document = e.getAnnotation(Document.class);
        addDocument(document, e, p);
    }

    private String getClassName(Element e) {

        Element localElement = e;
        while(!localElement.getKind().equals(ElementKind.CLASS) 
                && !localElement.getKind().equals(ElementKind.ENUM)
                && !localElement.getKind().equals(ElementKind.PACKAGE)
                && !localElement.getKind().equals(ElementKind.ANNOTATION_TYPE)){
            localElement = localElement.getEnclosingElement();
        }
        return localElement.toString();
    }

    private void keysCannontHaveSpaces(String key) {
        Pattern pattern = Pattern.compile("\\s");
        Matcher matcher = pattern.matcher(key);
        boolean foundWhitespace = matcher.find();
        if (foundWhitespace) {
            throw new RuntimeException("Key's cannot have white space");
        }
    }

}
