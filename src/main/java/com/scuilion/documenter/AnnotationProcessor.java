package com.scuilion.documenter;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;

@SupportedAnnotationTypes({"com.scuilion.documenter.Document"})
public class AnnotationProcessor extends AbstractProcessor {

    private List<Note> documents;

    public AnnotationProcessor() {
        super();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        documents = new ArrayList<>();
        if (roundEnv.processingOver() == false) {
            Scanner scanner = new Scanner();
            scanner.scan(roundEnv.getRootElements(), documents);
        }
        System.out.println(Arrays.toString(documents.toArray()));
        PropertyFile.update(documents, new File(""));
        return true;
    }

    @Override
    public SourceVersion getSupportedSourceVersion(){
        return SourceVersion.latestSupported();
    }
}