package com.t3g7.spark.facebook;

import java.util.Timer;

public class FacebookStreamingApp {
	public static void main(String[] args) {
		new FacebookUtils().facebookConfig();

		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new Streamer(), 0, 15);
	}
}
