package com.scuilion.documenter;

import java.util.ArrayList;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeKind;
import javax.lang.model.util.ElementScanner8;

public class Scanner extends ElementScanner8<ArrayList<String>, Void> { 

    @Override
    public ArrayList<String> visitPackage(PackageElement e, Void p) {
        System.out.println( "visitPackage");
        simplePrint(e);
        return super.visitPackage(e, p);
    }

    @Override
    public ArrayList<String> visitTypeParameter(TypeParameterElement e, Void p) {
        System.out.println( "visitTypeParameter");
        simplePrint(e);
        return super.visitTypeParameter(e, p);
    }

    @Override
    public ArrayList<String> visitType(TypeElement e, Void p) {
        System.out.println( "visitType");
        simplePrint(e);
        return super.visitType(e, p);
    }

    private void simplePrint(Element e) {
        Document document = e.getAnnotation(Document.class);

        System.out.print( document.priority());
        System.out.println(" executable " + e.getEnclosingElement().toString());
        System.out.println( e.getSimpleName());
        System.out.println( e.getSimpleName());
        System.out.println( );
    }

    @Override
    public ArrayList<String> visitExecutable(ExecutableElement e, Void p) {

        if(e.getReturnType().getKind() == TypeKind.DECLARED) {
            simplePrint(e);
        }else {
            System.out.println( "visitExecutable");
            System.out.println( e.getReturnType().toString());
            System.out.println( e.getReturnType().getKind().toString());
            System.out.println( e.getSimpleName().toString());
        }
        System.out.println();
        return super.visitExecutable(e, p);
    }

    @Override
    public ArrayList<String> visitVariable(VariableElement e, Void p) {
        System.out.println( "visitVariable");
        if(e.asType().getKind() == TypeKind.DECLARED) {
            simplePrint(e);
        }
        return super.visitVariable(e, p);
    } 
}
