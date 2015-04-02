package com.scuilion.documenter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.inject.Inject;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;

@SupportedAnnotationTypes({ "com.scuilion.documenter.Document" })
public class AnnotationProcessor extends AbstractProcessor {

    @Inject 
    Writer writer = new DefaultWriter();

    public AnnotationProcessor() {
        super();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Map<String, Note> documents;
        documents = new HashMap<>();
        if (!roundEnv.processingOver()) {
            Scanner scanner = new Scanner();
            scanner.scan(roundEnv.getElementsAnnotatedWith(com.scuilion.documenter.Document.class), documents);
            writer.write(documents);
        }
        return true;
    }


    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
}
