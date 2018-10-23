package com.connor.basic.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * CountDownLatch: 主要方法: await() countDown()  
 * 将对象CountDownLatch传入线程,控制线程的执行顺序
 * 
 * CyclicBarrier : 主要方法: await() 
 * 控制线程的汇总的一个总开关
 * 
 * Semaphore : 主要方法
 * 流量控制
 */
import com.connor.util.SleepUtils;

public class TestThreadTool {

	static CyclicBarrier cl = new CyclicBarrier(3);

	public static void main(String[] args) throws InterruptedException {
		
		//CyclicBarrier
		testCyclicBarrier();
		Thread.sleep(4000);
		cl.reset();
		System.out.println("down");
		
		//CountDownLatch
		//testCountDownLatch();
		
		//Semaphore
		//testSemaphore();
		
		//顺序执行T1-T2-T3
		//testCountDown();
	}
	/**
	 * 使用CountDownLatch 顺序执行T1-T2-T3
	 */
	private static void testCountDown(){
		final CountDownLatch c1 = new CountDownLatch(1);
		final CountDownLatch c2 = new CountDownLatch(1);
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("我是T3,我必须要等待T2执行");
					c2.await();
					System.out.println("我是T3,我执行完了");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("我是T2,我必须要等待T1执行");
					c1.await();
					System.out.println("我是T2,我执行完了");
					c2.countDown();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("我是T1,我执行完了");
				c1.countDown();;
			}
		}).start();
	}
	
	private static void testSemaphore() {
		
	}

	private static void testCyclicBarrier() {
		// CyclicBarrier
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("Thread1我的工作已经做完了,进入等待");
					Thread.sleep(2000);
					cl.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					e.printStackTrace();
				}
				System.out.println("hello 我是Thread1,其他线程已经都执行完了");
			}
		}).start();
		// CyclicBarrier
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("Thread2我的工作已经做完了,进入等待");
					cl.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					e.printStackTrace();
				}
				System.out.println("hello 我是Thread2,其他线程已经都执行完了");
			}
		}).start();
	}
	
	private static void testCountDownLatch() throws InterruptedException{
		CountDownLatch startSignal = new CountDownLatch(2);
		CountDownLatch doneSignal = new CountDownLatch(3);
		for (int i = 0; i < 3; ++i) // create and start threads
			new Thread(new Worker(startSignal, doneSignal)).start();

		System.out.println("do Something else");
		SleepUtils.killTime(2l);// don't let run yet
		startSignal.countDown(); // let all threads proceed
		startSignal.countDown(); // let all threads proceed
		System.out.println("do Something else");
		SleepUtils.killTime(2l);// don't let run yet
		doneSignal.await(); // wait for all to finish
	}

}

class Worker implements Runnable {
	private final CountDownLatch startSignal;
	private final CountDownLatch doneSignal;

	Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
		this.startSignal = startSignal;
		this.doneSignal = doneSignal;
	}

	public void run() {
		try {
			startSignal.await();
			doWork();
			doneSignal.countDown();
		} catch (InterruptedException ex) {

		}
	}

	void doWork() {
		System.out.println("i am working");
	}
}
