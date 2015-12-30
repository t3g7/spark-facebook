package com.t3g7.spark.facebook;

import java.util.List;
import java.util.TimerTask;
import java.util.stream.Collectors;

import facebook4j.*;

public class Streamer extends TimerTask {

	@Override
	public void run() {
//		FacebookUtils.extendToken();
		
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
		for (Post post : posts) {
			
		}
	}
}
