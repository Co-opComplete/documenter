package com.scuilion.documenter;

import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class DefaultWriter implements Writer {

    @Override
    public void write(Map<String, Note> documents) {
        String filename = "";
        if (new File("build").exists()){
            filename = "build/documents.json";
        } else if( new File("build-eclipse").exists()) {
            filename = "build-eclipse/documents.json";
        } else{
            throw new RuntimeException("Can not find build folder.");
        }
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
