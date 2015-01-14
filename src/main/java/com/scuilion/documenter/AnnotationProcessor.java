package com.scuilion.documenter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@SupportedAnnotationTypes({ "com.scuilion.documenter.Document" })
public class AnnotationProcessor extends AbstractProcessor {

	public AnnotationProcessor() {
		super();
	}

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		Map<String, Note> documents;
		documents = new HashMap<>();
		if (roundEnv.processingOver() == false) {
			Scanner scanner = new Scanner();
			scanner.scan(roundEnv.getRootElements(), documents);
			updateProperties(documents);
			updateJson(documents);
		}
		return true;
	}

	private void updateJson(Map<String, Note> documents) {
		String filename = "documents.json";

		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		String jsonRepresentation = gson.toJson(documents);

		try {

			FileWriter Filewriter = new FileWriter(filename);
			Filewriter.write(jsonRepresentation);
			Filewriter.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void updateProperties(Map<String, Note> documents) {
		// PropertyFile.update(documents, new File("documenter.properties"));
	}

	@Override
	public SourceVersion getSupportedSourceVersion() {
		return SourceVersion.latestSupported();
	}
}