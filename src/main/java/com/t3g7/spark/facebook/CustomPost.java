package com.t3g7.spark.facebook;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import facebook4j.FacebookException;
import facebook4j.IdNameEntity;
import facebook4j.Comment;


public class CustomPost implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7503589812001038748L;

	public String body;
	public long userId;
	public String userScreenName;
	public String lang;
	public Date createdAt;
	public int favoriteCount;
	public int retweetCount;
	public String tweetId;
	public List<String> userMentions;
	public String replyId;
	public String responseTime;
	public List<String> hashtags;
	public List<String> urls;
	public String sentiment;
	
	public CustomPost() {
		
	}

	public CustomPost(String body, long userId, String userName, String lang,
			Date createdAt, int likesCount, int sharesCount, String postId,
			List<IdNameEntity> userMentions, String account,
			List<String> hashtags, String url, String sentiment) {
		super();
		this.body = body;
		this.userId = userId;
		this.userScreenName = userName;
		this.lang = lang;
		this.createdAt = createdAt;
		this.favoriteCount = likesCount;
		this.retweetCount = sharesCount;
		this.tweetId = postId;
		this.userMentions = userMentions.stream()
				.map(userMention -> userMention.getName())
				.collect(Collectors.toList());
		this.hashtags = hashtags;
		this.urls = new ArrayList<String>();
		urls.add(url);
		this.sentiment = sentiment;	
		
		
		// Response time computation
		Comment reply = null;
		try {
			reply = FacebookUtils.getFirstResponse(postId, "");
		} catch (FacebookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (reply != null) {
			this.replyId = reply.getId();
			this.responseTime = ResponseTime.getInstance().getResponseTime(createdAt, reply.getCreatedTime());
		} else {
			this.replyId = "";
			this.responseTime = "null";
		}

	}

	public String getBody() {
		return body;
	}

	public long getUserId() {
		return userId;
	}

	public String getUserScreenName() {
		return userScreenName;
	}

	public String getLang() {
		return lang;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public int getFavoriteCount() {
		return favoriteCount;
	}

	public int getRetweetCount() {
		return retweetCount;
	}

	public String getTweetId() {
		return tweetId;
	}

	public List<String> getUserMentions() {
		return userMentions;
	}

	public String getReplyId() {
		return replyId;
	}

	public String getResponseTime() {
		return responseTime;
	}

	public List<String> getHashtags() {
		return hashtags;
	}

	public List<String> getUrls() {
		return urls;
	}

	public String getSentiment() {
		return sentiment;
	}	

}
