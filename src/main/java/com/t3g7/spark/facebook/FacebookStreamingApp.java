package com.t3g7.spark.facebook;

import java.util.Timer;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;

public class FacebookStreamingApp {
	public static void main(String[] args) {
		CassandraUtils cassandraUtils = CassandraUtils.getInstance();
		FacebookUtils facebookUtils = FacebookUtils.getInstance();

		// Set Spark configuration and context
		SparkConf conf = new SparkConf().setMaster("local[2]")
				.setAppName("FacebookStreamingApp")
				.set("spark.cassandra.connection.host", "localhost");
		SparkContext sc = new SparkContext(conf);

		cassandraUtils.setUp(conf);
		facebookUtils.facebookConfig();

		Timer timer = new Timer();
		// Beware : values below in miliseconds
		timer.scheduleAtFixedRate(new Streamer(), 0, 15 * 1000);
	}
}
