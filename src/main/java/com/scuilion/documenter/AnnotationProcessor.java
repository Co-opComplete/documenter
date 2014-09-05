package com.scuilion.documenter;

import javax.annotation.processing.*;
import javax.lang.model.element.*;
import javax.lang.model.element.Element;

import javax.tools.*;
import javax.lang.model.SourceVersion;
import java.util.Set;

@SupportedAnnotationTypes({"com.scuilion.documenter.Document"})
public class AnnotationProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        System.out.println("in process");
        Messager messager = processingEnv.getMessager();

        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(Document.class);

        //Set<ClassElement> classElements = roundEnv.getElementsAnnotatedWith(Document.class);

        for (Element te : elements) {
            System.out.println(te.getClass());
            System.out.println(te.getSimpleName());
            System.out.println("");
        }

        return true;
    }

    @Override
    public SourceVersion getSupportedSourceVersion(){
        return SourceVersion.latestSupported();
    }
}
