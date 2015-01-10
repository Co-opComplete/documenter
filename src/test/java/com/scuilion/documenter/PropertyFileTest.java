package com.scuilion.documenter;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class PropertyFileTest {

	@Rule
	public TemporaryFolder testFolder = new TemporaryFolder();

	@Test
	public void createFileTest() throws IOException {
		File propertyFile = testFolder.newFile("application.properties");
		propertyFile.createNewFile();
		List<String> documents = Arrays.asList("100", "200", "300");
		System.out.println(propertyFile);
		PropertyFile.update(documents, propertyFile);
		try (Stream<String> stream = Files.lines(Paths.get(propertyFile.toString()), Charset.defaultCharset())) {
			stream.forEach(System.out::println);
		}
	}
}
