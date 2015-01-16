package com.scuilion.documenter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import javax.enterprise.inject.Alternative;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class DefaultWriter implements Writer {

	@Override
	public void write(Map<String, Note> documents) {
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
}