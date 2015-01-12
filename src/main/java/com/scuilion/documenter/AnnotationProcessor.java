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

    public AnnotationProcessor() {
        super();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        List<Note> documents;
        documents = new ArrayList<>();
        if (roundEnv.processingOver() == false) {
            Scanner scanner = new Scanner();
            scanner.scan(roundEnv.getRootElements(), documents);
            updateProperties(documents);
            updateJson(documents);
        }
        return true;
    }

    private void updateJson(List<Note> documents) {
	}

	private void updateProperties(List<Note> documents) {
//        PropertyFile.update(documents, new File("documenter.properties"));
	}

	@Override
    public SourceVersion getSupportedSourceVersion(){
        return SourceVersion.latestSupported();
    }
}