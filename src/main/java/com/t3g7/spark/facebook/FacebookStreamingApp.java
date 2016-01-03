package com.t3g7.spark.facebook;

import java.util.Timer;

public class FacebookStreamingApp {
	public static void main(String[] args) {
		FacebookUtils facebookUtils = FacebookUtils.getInstance();
		
		facebookUtils.facebookConfig();

		Timer timer = new Timer();
		// Beware : values below in miliseconds 
		timer.scheduleAtFixedRate(new Streamer(), 0, 15 * 1000);
	}
}
