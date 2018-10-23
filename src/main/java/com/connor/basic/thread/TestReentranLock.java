package com.connor.basic.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 计数器
 * 
 * @author connor_zeng
 *
 */
public class TestReentranLock {

	private Integer i = 0;

	private ReentrantLock lock = new ReentrantLock();
	
	ReentrantReadWriteLock readWrite = new ReentrantReadWriteLock();

	public static void main(String[] args) {
		
		
		Long startMills = System.currentTimeMillis();

		final TestReentranLock c = new TestReentranLock();
		List<Thread> ts = new ArrayList<Thread>(100);
		for (int i = 0; i < 2; i++) {
			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						c.lockCount();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("down");
				}
			});
			ts.add(t);
		}

		for (Thread t : ts) {
			t.start();
		}

		Long endMills = System.currentTimeMillis();
		System.out.println(endMills - startMills);
	}


	private void lockCount() throws InterruptedException {
		
		
		
		if (lock.tryLock(10, TimeUnit.MILLISECONDS)){
			try {
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				i++;
			} finally {
				lock.unlock();
			}
		}
	}

}
