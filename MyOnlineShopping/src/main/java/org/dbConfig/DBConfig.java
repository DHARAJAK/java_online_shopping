package org.dbConfig;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBConfig {
	static String url;
	static String username;
	static String password;

	static {
		Properties props = new Properties();
		// Using GetClassLoader().getResourceAsStream reads the file from your Java
		// folder/classpath
		try (InputStream is = DBConfig.class.getClassLoader().getResourceAsStream("db.properties")) {

			if (is == null) {
				throw new FileNotFoundException("db.properties file not found inside the Java source folder!");
			}

			props.load(is);
			url = props.getProperty("db.url");
			username = props.getProperty("db.username");
			password = props.getProperty("db.password");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static public String getUrl() {
		return url;
	}

	static public String getUsername() {
		return username;
	}

	static public String getPassword() {
		return password;
	}
}