package com.scuilion.documenter;

import javax.annotation.processing.*;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.AnnotationMirror;
       
import javax.tools.*;
import javax.lang.model.SourceVersion;
import java.util.Set;
import java.util.Map;

@SupportedAnnotationTypes({"com.scuilion.documenter.Document"})
public class AnnotationProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        System.out.println("in process");
        Messager messager = processingEnv.getMessager();

        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(Document.class);
        for (Element te : elements) {
            for(AnnotationMirror annotationMirror : te.getAnnotationMirrors()){
                System.out.println("--------");
                for (Map.Entry<? extends ExecutableElement,? extends AnnotationValue> entry : annotationMirror.getElementValues().entrySet()) {
                    ExecutableElement key = entry.getKey();
                    AnnotationValue value = entry.getValue();
                    System.out.println(key);
                    System.out.println(value);
                }
                System.out.println("--------");
            }
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
