package com.t3g7.spark.facebook;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.stream.Collectors;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import com.datastax.spark.connector.japi.CassandraJavaUtil;

import facebook4j.*;

public class Streamer extends TimerTask {
	JavaSparkContext jsc;

	public Streamer(JavaSparkContext jsc) {
		// TODO Auto-generated constructor stub
		this.jsc = jsc;
	}

	@Override
	public void run() {
		// FacebookUtils.extendToken();

		for (String account : FacebookUtils.accounts) {
			try {
				processPosts(FacebookUtils.getPosts(account), account);
			} catch (FacebookException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void processPosts(ResponseList<Post> posts, String account) {
		List<CustomPost> results = posts
				.stream()
				.map(p -> new CustomPost(p.getMessage() == null ? "null" : p.getMessage(), 
						Long.parseLong(p.getFrom().getId()), 
						p.getFrom().getName(), 
						"FR", 
						p.getCreatedTime(), 
						p.getLikes().size(),
						p.getSharesCount() == null ? 0 : p.getSharesCount(), 
						p.getId(),
						p.getWithTags(), 
						account,
						new ArrayList<String>(p.getStoryTags().keySet()),
						p.getLink() == null ? "" : p.getLink().toString(),
						"null"
				)).collect(Collectors.toList());

		System.out.println(results);
		JavaRDD<CustomPost> postsRDD = jsc.parallelize(results);
		CassandraJavaUtil.javaFunctions(postsRDD)
				.writerBuilder("facebook_streaming", "tweets",
						CassandraJavaUtil.mapToRow(CustomPost.class))
				.saveToCassandra();
		
	}
}
