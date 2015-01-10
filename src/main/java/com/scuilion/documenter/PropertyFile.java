package com.scuilion.documenter;

import java.io.File;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class PropertyFile {

	public static void update(List<Note> notes, File name) {
		try {
			PropertiesConfiguration config = new PropertiesConfiguration(name.toString());
            notes.forEach(document -> config.addProperty(document.getKey(),document.getPriority()));
            config.save();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}

}
