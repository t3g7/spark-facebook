package com.t3g7.spark.facebook;

import java.util.Timer;

import facebook4j.Facebook;
import facebook4j.FacebookFactory;

public class FacebookStreamingApp {
    public static void main( String[] args ) {
    	Facebook facebook = new FacebookFactory().getInstance();
    	FacebookUtils.facebookConfig(facebook);
    	
    	Timer timer = new Timer();
    	timer.scheduleAtFixedRate(new Streamer(), 0, 15);
    	
    }
}
