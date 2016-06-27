package com.util;

public class PerformanceMonitor {
	
	private static ThreadLocal<MethodPerformance> performanceRecord = new ThreadLocal<MethodPerformance>();
	
	public static void begin(String method){
		System.out.println("begin monitor");
		MethodPerformance value = new MethodPerformance(method);
		performanceRecord.set(value);
	}
	public static void end(){
		System.out.println("end monitor");
		performanceRecord.get().printPerformance();
	}
	
}
