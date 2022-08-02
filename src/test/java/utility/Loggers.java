package utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Loggers {
	private static final String ANSI_RESET = "\u001B[0m";
	private static final String ANSI_RED = "\u001B[31m";
	private static final String ANSI_GREEN = "\u001B[32m";
	private static final String ANSI_YELLOW = "\u001B[33m";
	private static final String ANSI_CYAN = "\u001B[36m";
	private static final String ANSI_WHITE = "\u001B[37m";

	private static String getCurrentTimeAndDate() {
		LocalDateTime currDateTime = LocalDateTime.now();
		DateTimeFormatter formatForDateTime = DateTimeFormatter.ofPattern("dd-MMM-yyyy hh:mm:ss:a");
		String formattedDate = currDateTime.format(formatForDateTime);
		return "[ " + formattedDate + " ] : ";
	}

	private static String formattedMessage(String color, String message) {
		return getCurrentTimeAndDate() + color + message + ANSI_RESET;
	}

	public static void heading(String message) {
		System.out.println(formattedMessage(ANSI_CYAN, message));
	}

	public static void info(String message) {
		System.out.println(formattedMessage(ANSI_WHITE, message));
	}

	public static void pass(String message) {
		System.out.println(formattedMessage(ANSI_GREEN, message));
	}

	public static void error(String message) {
		System.out.println(formattedMessage(ANSI_RED, message));
	}

	public static void skip(String message) {
		System.out.println(formattedMessage(ANSI_YELLOW, message));
	}

	public static void nextLine() {
		System.out.println();
	}

	public static void breakLine() {
		System.out.println(
				"\n===========================================================================================\n");

	}
}
