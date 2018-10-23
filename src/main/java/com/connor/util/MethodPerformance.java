package com.connor.util;

public class MethodPerformance {
	
	private long begin;
	private long end;
	private String serviceMethod;
	
	public MethodPerformance(String serviceMethod){
		begin = System.currentTimeMillis();
		this.serviceMethod = serviceMethod;
	}
	
	public void printPerformance(){
		end = System.currentTimeMillis();
		long userTimes = end - begin;
		
		System.out.println(serviceMethod + " use time--" + userTimes);
	} 
}
