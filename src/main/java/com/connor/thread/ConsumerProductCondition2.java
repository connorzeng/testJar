package com.connor.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock Condition实现生产者消费者, 
 * 每次消费者消费了一个服务,就提醒生产者进行生产
 * 
 * @author connor_zeng
 *
 */
public class ConsumerProductCondition2 {

	private static ReentrantLock lock = new ReentrantLock();
	private static Condition lockCond = lock.newCondition();
	private static Integer resource = 3;

	public static void main(String[] args) {

		List<Thread> trds = new ArrayList<Thread>();

		for (int i = 0; i < 9; i++) {
			final int j = i;
			Thread pro = new Thread(new Runnable() {
				@Override
				public void run() {
					Integer resoruceget = Consumer.getResource();
					System.out.println("getResource:" + resoruceget + "  i:" + j );
				}
			});
			
			Thread pros = new Thread(new Runnable() {
				@Override
				public void run() {
					Product.prodResource();
				}
			});
			trds.add(pros);
			trds.add(pro);
		}

		for (Thread trd : trds) {
			trd.start();
		}
	}

	static class Product {
		public static void prodResource() {
			lock.lock();
			try {
				while (resource >= 3) {
					lockCond.await();
				}
				resource = 3;
				lockCond.signalAll();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}
	}

	static class Consumer {
		public static Integer getResource() {
			try {
				lock.lock();
				while (resource <= 0) {
					lockCond.await();
				}
				int result = resource --;
				lockCond.signalAll();
				return result;
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
			return -500;
		}
	}
}
