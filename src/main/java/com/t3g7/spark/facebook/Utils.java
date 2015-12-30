package com.t3g7.spark.facebook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sun.xml.internal.org.jvnet.fastinfoset.FastInfosetSource;

public class Utils {

	private Utils() {
	}

	private static Utils INSTANCE = new Utils();

	public static Utils getInstance() {
		return INSTANCE;
	}

	Long lastTimestamp = System.currentTimeMillis() / 1000 - 10000;

	public void setLastTimestamp() {
		lastTimestamp = System.currentTimeMillis() / 1000 - 10000;
	}

	public ArrayList<String> getLines(String resource) {
		InputStreamReader isr = new InputStreamReader(getClass().getResourceAsStream(resource));
		Scanner s = new Scanner(isr);

		ArrayList<String> lines = new ArrayList<String>();
		while (s.hasNext()) {
			lines.add(s.next());
		}
		s.close();

		return lines;
	}

	public String getLine(String resource) {
		InputStreamReader isr = new InputStreamReader(getClass().getResourceAsStream(resource));
		Scanner s = new Scanner(isr);
		
		String line = s.next();
		s.close();
		
		return line;
	}
}
