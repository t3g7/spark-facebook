package com.t3g7.spark.facebook;

import java.util.List;
import java.util.TimerTask;
import java.util.stream.Collectors;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
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

		List<Post> results = posts.stream().map(p -> {
			return p;
		}).collect(Collectors.toList());

		System.out.println(results);
		JavaRDD<Post> postsRDD = jsc.parallelize(results);
		// TODO : find a way to use the SaveToCassandra function 'cause the import we need cannot be found 
	}
}
