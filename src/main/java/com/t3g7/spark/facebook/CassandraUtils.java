package com.t3g7.spark.facebook;

import com.datastax.driver.core.Session;
import com.datastax.spark.connector.cql.CassandraConnector;

import org.apache.spark.SparkConf;

public class CassandraUtils {
	/**
	 * Class model is in the project Spark-Streaming-Twitter in file
	 * utils/CassandraSettings.scala
	 */
	private CassandraUtils() {
	}

	private static CassandraUtils INSTANCE = new CassandraUtils();

	public static CassandraUtils getInstance() {
		return INSTANCE;
	}

	public void setUp(SparkConf conf) {
		CassandraConnector connector = CassandraConnector.apply(conf);

		// TODO : Adapt the fields of the table created to the facebook data template
		try (Session session = connector.openSession()) {
			session.execute("CREATE KEYSPACE IF NOT EXISTS facebook_streaming WITH REPLICATION = {'class': 'SimpleStrategy', 'replication_factor': 1}");
			session.execute("CREATE TABLE IF NOT EXISTS facebook_streaming.tweets ("
					+ "body text,"
					+ "user_id bigint,"
					+ "user_screen_name text,"
					+ "lang text,"
					+ "created_at timestamp,"
					+ "favorite_count int,"
					+ "retweet_count int,"
					+ "post_id bigint,"
					+ "user_mentions list<text>,"
					+ "reply_id bigint,"
					+ "response_time text,"
					+ "hashtags list<text>,"
					+ "urls list<text>,"
					+ "sentiment text,"
					+ "PRIMARY KEY (body, user_id, post_id, user_screen_name, sentiment))");
			session.execute("CREATE TABLE IF NOT EXISTS facebook_streaming.freq ("
					+ "date timestamp,"
					+ "count counter,"
					+ "PRIMARY KEY (date))");
		}
	}
}
