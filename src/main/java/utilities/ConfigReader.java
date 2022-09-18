package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	public static Properties prop;
	public static String value;

	public static String getPropertyValue(String key) {

		prop = new Properties();

		try {
			FileInputStream propFile = new FileInputStream(
					System.getProperty("user.dir") + "/src/test/resources" + "/config.properties");
			prop.load(propFile);

			value = prop.getProperty(key);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return value;

	}
}
