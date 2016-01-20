package com.t3g7.spark.facebook;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import facebook4j.IdNameEntity;

public class CustomPost {
	public String body;
	public long userId;
	public String userName;
	public String lang;
	public Date createdAt;
	public int likesCount;
	public int sharesCount;
	public long postId;
	public List<String> userMentions;
	public long replyId;
	public String responseTime;
	public List<String> hashtags;
	public String sentiment;

	public CustomPost(String body, long userId, String userName, String lang,
			Date createdAt, int likesCount, int sharesCount, long postId,
			List<IdNameEntity> userMentions, long replyId, String responseTime,
			List<String> hashtags, String sentiment) {
		super();
		this.body = body;
		this.userId = userId;
		this.userName = userName;
		this.lang = lang;
		this.createdAt = createdAt;
		this.likesCount = likesCount;
		this.sharesCount = sharesCount;
		this.postId = postId;
		this.userMentions = userMentions.stream()
				.map(userMention -> userMention.getName())
				.collect(Collectors.toList());
		this.replyId = replyId;
		this.responseTime = responseTime;
		this.hashtags = hashtags;
		this.sentiment = sentiment;
	}

}
