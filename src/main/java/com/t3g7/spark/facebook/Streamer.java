package com.t3g7.spark.facebook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimerTask;
import java.util.stream.Collectors;

import org.apache.hadoop.yarn.webapp.hamlet.HamletSpec.P;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import com.datastax.driver.core.Session;
import com.datastax.spark.connector.cql.CassandraConnector;
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
				processPosts(FacebookUtils.getPosts(account));
			} catch (FacebookException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void processPosts(ResponseList<Post> posts) {
		System.out.println("Process befeore");

		List<CustomPost> results = posts
				.stream()
				.map(p -> new CustomPost(p.getMessage(), Long.parseLong(p
						.getFrom().getId()), p.getFrom().getName(), "FR", p
						.getCreatedTime(), p.getLikes().getCount(), // If this
																	// doesn't
																	// work try
																	// p.getLikes().getSummary().getTotalCount();
						p.getSharesCount(), Long.parseLong(p.getId()), p
								.getWithTags(), Long.parseLong(p.getComments()
								.get(0).getId()),
						"0", // TODO : Compute response time
						new ArrayList<String>(p.getStoryTags().keySet()),
						"null" // TODO : Compute sentiment
				)).collect(Collectors.toList());

		System.out.println(results);
		JavaRDD<CustomPost> postsRDD = jsc.parallelize(results);
		CassandraJavaUtil.javaFunctions(postsRDD)
				.writerBuilder("facebook_streaming", "tweets",
						CassandraJavaUtil.mapToRow(CustomPost.class))
				.saveToCassandra();
		
	}
}
