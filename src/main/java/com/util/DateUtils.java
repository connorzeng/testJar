package com.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	
	public static Date formatDate(String time){  
	    SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    try {  
	        return sdf.parse(time);  
	    } catch (ParseException e) {  
	        e.printStackTrace();  
	        return null;  
	    }  
	}  
	
	public static Date fromISODate(String time){
	    if(!time.matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}.\\d{3}Z")){
	        return null;
	    }
	    time=time.replaceFirst("T", " ").replaceFirst(".\\d{3}Z", "");  
	    Date date=formatDate(time);  
	    Calendar ca=Calendar.getInstance();  
	    ca.setTime(date);  
	    ca.add(Calendar.HOUR_OF_DAY, 8);  
	    return ca.getTime();  
	}
	
	public static void main(String[] args) {
		System.out.println(fromISODate("2016-10-07T00:15:00.000Z"));
	}
}
