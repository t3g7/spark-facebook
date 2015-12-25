package com.t3g7.spark.facebook;


import facebook4j.Facebook;

/**
 * Hello world!
 *
 */
public class FacebookStreamingApp {
    public static void main( String[] args ) {
    	// Traduction littérale du scala
    	Facebook facebook = FacebookUtils.facebookConfig();
    	
    	FacebookUtils.createStream();
    }
}
