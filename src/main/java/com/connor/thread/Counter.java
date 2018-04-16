package com.connor.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 计数器
 * 
 * @author connor_zeng
 *
 */
public class Counter {

	private Integer i = 0;
	private AtomicInteger atomicI = new AtomicInteger(0);

	private ReentrantLock lock = new ReentrantLock();
	private ReentrantLock fairLock = new ReentrantLock(true);

	public static void main(String[] args) {
		
		int j = 1;
		int f = j++;
		int a = 1;
		int b = ++a;
		System.out.println(f + "==" + b);
		
		Long startMills = System.currentTimeMillis();

		final Counter c = new Counter();
		List<Thread> ts = new ArrayList<Thread>(100);
		for (int i = 0; i < 4; i++) {
			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					for(int j=0;j<100;j++){
						//c.count();
						//c.safeCount();
						//c.syncCount();
						c.lockCount();
					}
				}
			});
			ts.add(t);
		}

		for (Thread t : ts) {
			try {
				t.start();
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		Long endMills = System.currentTimeMillis();
		System.out.println("result i = " + c.i);
		System.out.println(c.atomicI.get());
		System.out.println(endMills - startMills);
	}

	private void safeCount() {
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (;;) {
			int i = atomicI.get();
			if (atomicI.compareAndSet(i, ++i)) {
				break;
			}
		}
	}

	private void count() {
		i++;
	}

	private void syncCount() {
		synchronized (this) {
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			i++;
		}
	}

	private void lockCount() {
		lock.lock();
		try {
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			i++;
		} finally {
			lock.unlock();
		}
	}

}
