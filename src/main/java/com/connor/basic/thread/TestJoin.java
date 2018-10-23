package com.connor.basic.thread;

/**
 * 测试JOIN
 * @author connor_zeng
 *1)现在有T1、T2、T3三个线程，你怎样保证T2在T1执行完后执行，T3在T2执行完后执行？
 *这个线程问题通常会在第一轮或电话面试阶段被问到，目的是检测你对”join”方法是否熟悉。这个多线程问题比较简单，可以用join方法实现。
 */
public class TestJoin {
	
	public static void main(String[] args) {
		Thread currentThread = Thread.currentThread(); 
		for(int i=0;i<=10;i++){
			Thread1 t = new Thread1(currentThread);
			t.start();
			currentThread = t;
		}
	}
	
}

class Thread1 extends Thread{
	
	private Thread thread;
	
	public Thread1 (Thread thread){
		this.thread = thread;
	}
	
	@Override
	public void run() {
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("thread" + currentThread().getName());
	}
	
}