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

public class Scanner extends ElementScanner8<ArrayList<String>, List<String>> { 
    @Override
    public ArrayList<String> visitPackage(PackageElement e, List<String> p) {
        addDocument(e, p);
        return super.visitPackage(e, p);
    }

    @Override
    public ArrayList<String> visitTypeParameter(TypeParameterElement e, List<String> p) {
        addDocument(e, p);
        return super.visitTypeParameter(e, p);
    }

    @Override
    public ArrayList<String> visitType(TypeElement e, List<String> p) {
        addDocument(e, p);
        return super.visitType(e, p);
    }

    @Override
    public ArrayList<String> visitExecutable(ExecutableElement e, List<String> p) {
    	addDocument(e.getEnclosingElement(), p);
        return super.visitExecutable(e, p);
    }

    @Override
    public ArrayList<String> visitVariable(VariableElement e, List<String> p) {
    	addDocument(e, p);
        return super.visitVariable(e, p);
    } 

    protected void addDocument(Element e, List<String> p) {
        p.add(Integer.toString(e.getAnnotation(Document.class).priority()));
    }

}
