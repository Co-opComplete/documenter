package com.scuilion.documenter;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.ElementScanner8;

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
    public HashMap<String, Note> visitExecutable(ExecutableElement e, Map<String, Note> p) {
        System.out.println(e.getSimpleName());
        System.out.print("here ");
        //    	System.out.println(e.getReturnType().toString());
        if (!e.getSimpleName().contentEquals("<init>") && e.getKind().equals(ElementKind.METHOD)) {
            addDocument(e.getEnclosingElement(), p, e.getSimpleName().toString());
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

    protected void addDocument(Element e, Map<String, Note> p, String extendedName) {
        if (!extendedName.equals("")) {
            extendedName = "." + extendedName;
        }
        Document document = e.getAnnotation(Document.class);
        if (document != null) {
            int priority = document.priority();
            keysCannontHaveSpaces(document.key());
            String shortKey = document.key();
            String className = e.toString();
            String fullKey = className + "." + shortKey + extendedName;
            Note note = new Note(fullKey, priority, className, e.getKind());
            p.put(fullKey, note);
        }
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
