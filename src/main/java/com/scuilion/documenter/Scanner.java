package com.scuilion.documenter;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeKind;
import javax.lang.model.util.ElementScanner8;

public class Scanner extends ElementScanner8<Void, Void> { 

    @Override
    public Void visitExecutable(ExecutableElement e, Void p) {
        if(e.getReturnType().getKind() == TypeKind.DECLARED) {
            //Parameter parameter = e.getAnnotation(Document.class);

            System.out.println("executable " + e.getEnclosingElement().toString());
        }

        return super.visitExecutable(e, p);
    }
    @Override
    public Void visitVariable(VariableElement e, Void p) {
        if(e.asType().getKind() == TypeKind.DECLARED) {
            //Action action = e.getAnnotation(Document.class);
            System.out.println("variable " + e.getEnclosingElement().toString());
        }
        return super.visitVariable(e, p);
    } 
}
