package com.connor.util;

import java.util.concurrent.TimeUnit;

public class SleepUtils {
	
	public static final void second(long seconds){
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
		    System.out.println(Thread.currentThread().getName() + " interrupted");
			e.printStackTrace();
		}
	}
	
	public static final void killTime(long senconds){
	    long timesBegin = System.currentTimeMillis();
	    while(true){
	        long timeEnd = System.currentTimeMillis();
	        if (TimeUnit.MILLISECONDS.toSeconds(timeEnd - timesBegin) >= senconds){
	            break;
	        }
	    }
	}
	
}
