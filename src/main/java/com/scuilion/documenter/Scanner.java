package com.scuilion.documenter;

import java.util.HashMap;
import java.util.Map;

import javax.lang.model.element.Element;
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
//    	ifConstructor(e, p);
    	System.out.println(e.getSimpleName());
//    	System.out.println(e.getReturnType().toString());
    	addDocument(e.getEnclosingElement(), p);
        return super.visitExecutable(e, p);
    }

    private void ifConstructor(ExecutableElement e, Map<String, Note> p) {
    	if (e.getSimpleName().toString().equals("<init>")) {
    		System.out.println("it's the constur");
    		Document d =e.getAnnotation(Document.class);
            addDocument(e, p);
    	}
	}

	@Override
    public HashMap<String, Note> visitVariable(VariableElement e, Map<String, Note> p) {
    	addDocument(e, p);
        return super.visitVariable(e, p);
    } 

    protected void addDocument(Element e, Map<String, Note> p) {
        int priority = e.getAnnotation(Document.class).priority();
        String shortKey = e.getAnnotation(Document.class).key();
        String className = e.toString();
        String fullKey =  className + "." + shortKey;
    	Note note = new Note(fullKey, priority, className);
        p.put(fullKey, note);
    }

}
