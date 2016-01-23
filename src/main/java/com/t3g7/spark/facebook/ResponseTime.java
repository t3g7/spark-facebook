package com.t3g7.spark.facebook;

import java.util.Date;

import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

public class ResponseTime {
	private ResponseTime() {
	}

	private static ResponseTime INSTANCE = new ResponseTime();

	public static ResponseTime getInstance() {
		return INSTANCE;
	}

	public String getResponseTime(Date postTimestamp, Date replyTimestamp) {
		Period diff = new Period(replyTimestamp.getTime(),
				postTimestamp.getTime());
		PeriodFormatter hms = new PeriodFormatterBuilder()
				.minimumPrintedDigits(2).printZeroAlways().appendHours()
				.appendSeparator(":").appendMinutes().appendSuffix(":")
				.appendSeconds().toFormatter();
		
		return hms.print(diff);
	}
}
