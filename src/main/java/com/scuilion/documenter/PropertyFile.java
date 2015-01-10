package com.scuilion.documenter;

import java.io.File;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class PropertyFile {

	public static void update(List<String> documents, File name) {
		try {
			PropertiesConfiguration config = new PropertiesConfiguration(name.toString());
            documents.forEach(document -> config.addProperty(document,"x"));
            config.save();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}

}
