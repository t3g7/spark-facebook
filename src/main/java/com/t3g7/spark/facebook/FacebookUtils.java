package com.t3g7.spark.facebook;

import facebook4j.*;
import facebook4j.auth.AccessToken;

import java.util.ArrayList;

public class FacebookUtils {

	private FacebookUtils() {
		// TODO Auto-generated constructor stub
	}

	private static FacebookUtils INSTANCE = new FacebookUtils();

	public static FacebookUtils getInstance() {
		return INSTANCE;
	}

	static Utils utils = Utils.getInstance();

	ArrayList<String> accounts = utils.getLines("/facebookPages.txt");

	static Facebook facebook = new FacebookFactory().getInstance();;

	public Facebook facebookConfig() {
		// TODO Auto-generated method stub
		String token = utils.getLine("/token.txt");

		facebook.setOAuthAppId("", "");
		facebook.setOAuthAccessToken(new AccessToken(token));

		return facebook;
	}

	public void extendToken() {
		/**
		 * to fix : error message - Missing client_id parameter. code - 101
		 */
//		String token = utils.getLine("/token.txt");
		try {
			facebook.extendTokenExpiration();
		} catch (FacebookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ResponseList<Post> getPosts(String page) throws FacebookException {
		Reading parameters = new Reading().addParameter("since",
				utils.lastTimestamp).fields("from", "message", "created_time",
				"comments", "story_tags");

		return facebook.getFeed(page, parameters);
	}

	public void plop() {
		// TODO Auto-generated method stub
		System.out.println("plop");
	}

}
