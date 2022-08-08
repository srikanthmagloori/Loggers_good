package utility;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyReader {

	private static Properties propInstance;

	public static Properties getInstance() {

		if (propInstance == null) {
			propInstance = new Properties();
			try {
				String propertyFilePath = ".\\src\\test\\resources\\configuration\\setting.property";
				Loggers.config("Property file path " + propertyFilePath);

				propInstance.load(new FileInputStream(propertyFilePath));
			} catch (Exception e) {
				Loggers.error("Error with reading property File " + e.getMessage());
			}
			return propInstance;
		} else {
			return propInstance;
		}
	}
}
