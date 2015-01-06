package com.scuilion.documenter;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeKind;
import javax.lang.model.util.ElementScanner8;

public class Scanner extends ElementScanner8<ArrayList<String>, List<String>> { 

    @Override
    public ArrayList<String> visitTypeParameter(TypeParameterElement e, List<String> p) {
        System.out.println( "visitTypeParameter");
        addDocument(e, p);
        return super.visitTypeParameter(e, p);
    }

    @Override
    public ArrayList<String> visitType(TypeElement e, List<String> p) {
        System.out.println( "visitType");
        addDocument(e, p);
        return super.visitType(e, p);
    }

    @Override
    public ArrayList<String> visitExecutable(ExecutableElement e, List<String> p) {
            System.out.println( "visitExecutable");
        if(e.getReturnType().getKind() == TypeKind.DECLARED) {
        }else {
//            System.out.println( e.getReturnType().toString());
//            System.out.println( e.getReturnType().getKind().toString());
//            System.out.println( e.getSimpleName().toString());
        }
//        System.out.println();
        return super.visitExecutable(e, p);
    }

    @Override
    public ArrayList<String> visitVariable(VariableElement e, List<String> p) {
        System.out.println( "visitVariable");
        if(e.asType().getKind() == TypeKind.DECLARED) {
        }
        return super.visitVariable(e, p);
    } 

    private void addDocument(Element e, List<String> p) {
        p.add(Integer.toString(e.getAnnotation(Document.class).priority()));
    }

}
