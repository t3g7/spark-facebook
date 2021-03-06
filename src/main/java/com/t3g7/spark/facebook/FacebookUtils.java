package com.t3g7.spark.facebook;

import facebook4j.*;
import facebook4j.auth.AccessToken;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class FacebookUtils {
	static Utils utils = Utils.getInstance();

	static ArrayList<String> accounts = utils.getLines("/facebookPages.txt");

	static Facebook facebook = new FacebookFactory().getInstance();;

	public static Facebook facebookConfig() {
		// TODO Auto-generated method stub
		String token = utils.getLine("/token.txt");

		facebook.setOAuthAppId("", "");
		facebook.setOAuthAccessToken(new AccessToken(token));

		return facebook;
	}

	public static void extendToken() {
		/**
		 * to fix : error message - Missing client_id parameter. code - 101
		 */
		try {
			facebook.extendTokenExpiration();
		} catch (FacebookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static ResponseList<Post> getPosts(String page) throws FacebookException {
		Reading parameters = new Reading().addParameter("since",
				utils.lastTimestamp).fields("from", "message", "created_time",
				"comments", "story_tags","likes","shares", "link");

		return facebook.getFeed(page, parameters);
	}
	
	public static Comment getFirstResponse(String postId, String from) throws FacebookException {
		Reading parameters = new Reading().order(Ordering.CHRONOLOGICAL).limit(10).fields("from","created_time");
		ResponseList<Comment> comments = facebook.getPostComments(postId, parameters);
		ArrayList <Comment> commentsFrom = (ArrayList<Comment>) comments.stream()
				.filter(c -> c.getFrom().getName() == from).collect(Collectors.toList());
		
		if (! commentsFrom.isEmpty()) {
			return commentsFrom.get(0);
		} else {
			return null;
		}
	}

}
