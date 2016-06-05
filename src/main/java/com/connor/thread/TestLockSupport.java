package com.connor.thread;

import java.util.concurrent.locks.LockSupport;


/**
 * 测试LockSupport
 * @author connor_zeng
 *
 */
public class TestLockSupport extends Thread{

	private Thread thread = null; 
	
	public TestLockSupport(Thread thread) {
		this.thread = thread;
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(5* 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		LockSupport.unpark(thread);
	} 
	
	public static void main(String[] args) {
		
		Thread thread = new TestLockSupport(Thread.currentThread());
		thread.start();
		LockSupport.park();
		System.out.println("i am here");
	}
	
}
