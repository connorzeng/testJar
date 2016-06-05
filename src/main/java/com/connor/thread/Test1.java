package com.connor.thread;

import com.connor.tool.SleepUtils;


public class Test1 {
	
	public static void main(String[] args) {
		
		
		Thread busy = new Thread(new BusyRunner(),"busyRnner");
		Thread sleep = new Thread(new SleepRunner(),"sleepRunner");
		busy.setDaemon(true);
		sleep.setDaemon(true);
		busy.start();
		sleep.start();
		SleepUtils.second(5);
		
		busy.interrupt();
		sleep.interrupt();
		System.out.println(busy.isInterrupted());
		System.out.println(sleep.isInterrupted());
		SleepUtils.second(7);
	}
	
	
	
}
class TimeWaiting implements Runnable{
	@Override
	public void run() {
		SleepUtils.second(2);
	}
}

class SleepRunner implements Runnable{
	@Override
	public void run() {
		while(true){
			SleepUtils.second(10);
			System.out.println("wake up");
		}
	}
}

class BusyRunner implements Runnable{
	@Override
	public void run() {
		while(true){
			
		}
	}
}