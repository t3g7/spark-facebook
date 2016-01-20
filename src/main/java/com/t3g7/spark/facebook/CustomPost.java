package com.t3g7.spark.facebook;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import facebook4j.IdNameEntity;

public class CustomPost {
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

	public CustomPost(String body, long userId, String userName, String lang,
			Date createdAt, int likesCount, int sharesCount, String postId,
			List<IdNameEntity> userMentions, String replyId, String responseTime,
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
		this.replyId = replyId;
		this.responseTime = responseTime;
		this.hashtags = hashtags;
		this.urls = new ArrayList<String>();
		urls.add(url);
		this.sentiment = sentiment;
		
		System.out.println(this);
	}

}
