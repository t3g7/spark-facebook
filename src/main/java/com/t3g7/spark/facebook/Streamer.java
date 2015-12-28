package com.t3g7.spark.facebook;

import java.util.List;
import java.util.TimerTask;
import java.util.stream.Collectors;

import facebook4j.*;

public class Streamer extends TimerTask {

	@Override
	public void run() {
		FacebookUtils.extendToken();
		for (String account : FacebookUtils.accounts) {
			System.out.println(account);
			try {
				processPosts(FacebookUtils.getPosts(account));
			} catch (FacebookException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void processPosts(ResponseList<Post> posts) {
		List<Post> results = posts.stream().map(p ->  p)
				.collect(Collectors.toList());
		System.out.println(results);
		for (Post post : posts) {
			// actions
			System.out.println("Process befeore");
			System.out.println(post);
		}
	}
}
