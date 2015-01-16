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

import org.jboss.weld.environment.se.StartMain;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

@SupportedAnnotationTypes({ "com.scuilion.documenter.Document" })
public class AnnotationProcessor extends AbstractProcessor {

	@Inject 
	Writer writer; 

	public AnnotationProcessor() {
		super();
	}

	private void injectWriter() {
		Weld weld = new Weld();
        WeldContainer container = weld.initialize();
        writer = container.instance().select(Writer.class).get();
        weld.shutdown();
	}

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		injectWriter();
		String[] args = null;
		StartMain.main(args);
		Map<String, Note> documents;
		documents = new HashMap<>();
		if (!roundEnv.processingOver()) {
			Scanner scanner = new Scanner();
			scanner.scan(roundEnv.getRootElements(), documents);
			updateProperties(documents);
//			updateJson(documents);
			writer.write(documents);
		}
		return true;
	}


	private void updateProperties(Map<String, Note> documents) {
		// PropertyFile.update(documents, new File("documenter.properties"));
	}

	@Override
	public SourceVersion getSupportedSourceVersion() {
		return SourceVersion.latestSupported();
	}
}