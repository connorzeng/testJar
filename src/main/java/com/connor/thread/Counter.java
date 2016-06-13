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

	private static Integer i = 0;
	private AtomicInteger atomicI = new AtomicInteger(0);

	private ReentrantLock lock = new ReentrantLock();
	private ReentrantLock fairLock = new ReentrantLock(true);

	public static void main(String[] args) {

		final Counter c = new Counter();

		List<Thread> ts = new ArrayList<Thread>(600);
		Long startMills = System.currentTimeMillis();
		for (int i = 0; i < 100; i++) {
			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					for (int j = 0; j < 100; j++) {
						// c.count();
						// c.safeCount();
						c.syncCount();
						// c.lockCount();
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
		System.out.println(i);
		System.out.println(c.atomicI.get());
		System.out.println(endMills - startMills);
	}

	private void safeCount() {
		try {
			Thread.sleep(10);
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
		synchronized (i) {
			i++;
		}
	}

	private void lockCount() {
		lock.lock();
		try {
			i++;
		} finally {
			lock.unlock();
		}
	}

}
