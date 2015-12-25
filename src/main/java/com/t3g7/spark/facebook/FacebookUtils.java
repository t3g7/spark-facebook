package com.t3g7.spark.facebook;

import facebook4j.*;
import facebook4j.auth.AccessToken;

import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class FacebookUtils {

	static Utils utils = Utils.getInstance();

	static Facebook facebook = new FacebookFactory().getInstance();
	
	ArrayList<String> accounts = utils.getLines("/facebookPages.txt");

	public static Facebook facebookConfig() {
		// TODO Auto-generated method stub
		String token = utils.getLine("/token.txt");

		facebook.setOAuthAppId("", "");
		facebook.setOAuthAccessToken(new AccessToken(token));

		return facebook;
	}

	public static void extendToken() {
		String token = utils.getLine("/token.txt");
		try {
			facebook.extendTokenExpiration(token);
		} catch (FacebookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static ResponseList<Post> getPosts(String page) throws FacebookException {
		Reading parameters = new Reading().addParameter("since", utils.lastTimestamp)
				.fields("from", "message", "created_time", "comments","story_tags");
		
		return facebook.getFeed(page, parameters);
	}
	
	

	public static void createStream() {
		// TODO Auto-generated method stub

	}

}
