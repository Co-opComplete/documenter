package com.scuilion.documenter;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.ElementScanner8;

public class Scanner extends ElementScanner8<ArrayList<Note>, List<Note>> { 
    @Override
    public ArrayList<Note> visitPackage(PackageElement e, List<Note> p) {
        addDocument(e, p);
        return super.visitPackage(e, p);
    }

    @Override
    public ArrayList<Note> visitTypeParameter(TypeParameterElement e, List<Note> p) {
        addDocument(e, p);
        return super.visitTypeParameter(e, p);
    }

    @Override
    public ArrayList<Note> visitType(TypeElement e, List<Note> p) {
        addDocument(e, p);
        return super.visitType(e, p);
    }

    @Override
    public ArrayList<Note> visitExecutable(ExecutableElement e, List<Note> p) {
//    	ifConstructor(e, p);
    	System.out.println(e.getSimpleName());
//    	System.out.println(e.getReturnType().toString());
    	addDocument(e.getEnclosingElement(), p);
        return super.visitExecutable(e, p);
    }

    private void ifConstructor(ExecutableElement e, List<Note> p) {
    	if (e.getSimpleName().toString().equals("<init>")) {
    		System.out.println("it's the constur");
    		Document d =e.getAnnotation(Document.class);
            addDocument(e, p);
    	}

	}

	@Override
    public ArrayList<Note> visitVariable(VariableElement e, List<Note> p) {
    	addDocument(e, p);
        return super.visitVariable(e, p);
    } 

    protected void addDocument(Element e, List<Note> p) {
        int priority = e.getAnnotation(Document.class).priority();
        String key = e.getAnnotation(Document.class).key();
    	Note note = new Note(key, priority);
        p.add(note);
    }

}
