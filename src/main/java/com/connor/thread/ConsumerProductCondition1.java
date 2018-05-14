package com.connor.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock Condition实现生产者消费者, 全部消耗完才通知生产者进行创建
 * 
 * @author connor_zeng
 *
 */
public class ConsumerProductCondition1 {

	private static ReentrantLock lock = new ReentrantLock();
	private static Condition consumerCond = lock.newCondition();
	private static Condition productCond = lock.newCondition();
	private static Integer resource = 0;

	public static void main(String[] args) {

		List<Thread> trds = new ArrayList<Thread>();
		for (int i = 0; i < 2; i++) {
			Thread pro = new Thread(new Runnable() {
				@Override
				public void run() {
					Product.prodResource();
				}
			});
			trds.add(pro);
		}

		for (int i = 0; i < 1000; i++) {
			final int j = i;
			Thread pro = new Thread(new Runnable() {
				@Override
				public void run() {
					Integer resoruceget = Consumer.getResource();
					System.out.println("getResource:" + resoruceget + "  i:" + j );
				}
			});
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
				while (true) {
					if (resource > 0) {
						consumerCond.signalAll();
						productCond.await();
					} else {
						System.out.println(Thread.currentThread().getId());
						resource = 3;
					}
				}
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
				if (resource > 0) {
					return resource--;
				}else{
					productCond.signalAll();
					consumerCond.await();
					return getResource();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
			return -500;
		}
	}
}
