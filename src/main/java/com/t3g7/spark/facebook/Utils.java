package com.t3g7.spark.facebook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Utils {
	
	private Utils() {}
	
	private static Utils INSTANCE = new Utils();
	
	public static Utils getInstance() {
		return INSTANCE;
	}
	
	Long lastTimestamp = System.currentTimeMillis() / 1000;


	public void setLastTimestamp () {
		lastTimestamp = System.currentTimeMillis() / 1000;
	}
	
	public ArrayList<String> getLines (String resource) {
		InputStreamReader isr = new InputStreamReader(this.getClass().getResourceAsStream(resource));
		Scanner s = new Scanner(isr);
		
		ArrayList<String> lines = new ArrayList<String>();
		
		while (s.hasNext()) {
			lines.add(s.next());
		}
		s.close();
		
		return lines;
	}
	
	public String getLine (String resource) {
		InputStreamReader isr = new InputStreamReader(this.getClass().getResourceAsStream(resource));
		BufferedReader br = new BufferedReader(isr);
		
		String line = "";
		try {
			line = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return line;
	}
}
