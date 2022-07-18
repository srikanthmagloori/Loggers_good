package test;

import java.util.Date;
import java.util.Properties;

import utility.PropertyReader;

public class Tests {

	public static void main(String[] args) {
		Properties prop = PropertyReader.getInstance();
		System.out.println(prop.getProperty("URL"));

		String b = null;
		System.out.println(b == null);

		Date date = new Date();
		String newDate = date.toString().replaceAll(" ", "").replaceAll(":", "");
		System.out.println(newDate);
	}

}
