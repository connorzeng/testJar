package com.connor.basic.thread;


public class SyncThread implements Runnable {
	private static int count;

	public SyncThread() {
		count = 0;
	}

	public void run() {
		synchronized (this) {
			for (int i = 0; i < 5; i++) {
				System.out.println(Thread.currentThread().getName() + ":"
						+ (count++));
			}
		}
	}

	public int getCount() {
		return count;
	}

	public static void main(String[] args) {
		SyncThread syncThread = new SyncThread();

		Thread thread1 = new Thread(syncThread, "SyncThread1");
		Thread thread2 = new Thread(syncThread, "SyncThread2");
		thread1.start();
		thread2.start();

		/*
		 * List<Thread> ls = new ArrayList<Thread>(); for (int i=0; i<10000;
		 * i++){ Thread thread = new Thread(syncThread); ls.add(thread); } for
		 * (Thread thread : ls){ thread.start(); } for (Thread thread : ls){ try
		 * { thread.join(); } catch (InterruptedException e) {
		 * e.printStackTrace(); } }
		 */

		System.out.println(count);

	}
}