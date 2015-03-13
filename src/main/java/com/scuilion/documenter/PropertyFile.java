package com.scuilion.documenter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class PropertyFile {

    private static File translationLocation = new File("translations");
    private static String propertyFileName = "message";

    public static void update(List<Note> notes) {
        try {
            for (File file : getListOfProperties()) {
                PropertiesConfiguration config = new PropertiesConfiguration(file);
                notes.forEach(document -> config.addProperty(document.getKey(),document.getPriority()));
                config.save();
            }
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    protected static File getFileName() {
        return getFileName(Locale.ROOT);
    }

    protected static File getFileName(Locale locale) {
        String localeString = locale.toString().equals("")?"":"_" + locale.toString();
        return new File(translationLocation, propertyFileName + localeString + ".properties");
    }
    
    protected static List<File> getListOfProperties() {
        File defaultProperties = new File(translationLocation, propertyFileName + ".properties");
        try {
            createDefault(defaultProperties);
            return Files.list(translationLocation.toPath())
                            .filter(p -> p.getFileName().toString().startsWith(propertyFileName))
                            .map(Path::toFile)
                            .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void createDefault(File defaultProperties) throws IOException {
        if (!translationLocation.exists()) {
            translationLocation.mkdir();
        }
        if (!defaultProperties.exists()) {
            defaultProperties.createNewFile();
        }
    }

    public static void setTranslationLocation(File translationLocation) {
        PropertyFile.translationLocation = translationLocation;
    }

    public static String getPropertyFileName() {
        return propertyFileName;
    }
}
