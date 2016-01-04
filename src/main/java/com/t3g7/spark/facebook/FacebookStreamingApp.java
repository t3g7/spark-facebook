package com.t3g7.spark.facebook;

import java.util.Timer;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

public class FacebookStreamingApp {
	public static void main(String[] args) {
		CassandraUtils cassandraUtils = CassandraUtils.getInstance();
		FacebookUtils facebookUtils = new FacebookUtils();

		// Set Spark configuration and context
		SparkConf conf = new SparkConf().setMaster("local[2]")
				.setAppName("FacebookStreamingApp")
				.set("spark.cassandra.connection.host", "localhost");
		JavaSparkContext jsc = new JavaSparkContext(conf);
		
		cassandraUtils.setUp(conf);
		facebookUtils.facebookConfig();

		Timer timer = new Timer();
		// Beware : values below in milliseconds
		timer.scheduleAtFixedRate(new Streamer(jsc), 0, 15 * 1000);
	}
}
