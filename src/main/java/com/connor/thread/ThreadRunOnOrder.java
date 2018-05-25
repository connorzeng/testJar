package com.connor.thread;

/**
 * 多线程执行打印 AAA BBB CCC AAA BBB CCC
 * 
 * @author connor
 *
 */
public class ThreadRunOnOrder {

	static class ThreadA extends Thread {
		@Override
		public void run() {
			synchronized (ThreadRunOnOrder.class) {
				try {
					ThreadRunOnOrder.class.wait(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("gogogo");
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {

		Thread threadA = new ThreadA();
		threadA.start();

		Thread.sleep(1000);
		synchronized (ThreadRunOnOrder.class) {
			ThreadRunOnOrder.class.notify();
		}
		
		
		Thread a = new Thread(new Task("A",0));
        Thread b = new Thread(new Task("B",1));
        Thread c = new Thread(new Task("C",2));
        c.start();
        b.start();
        a.start();
	}

	static class Task implements Runnable {

		private String one;
		private int count;
		private static int num = 0;

		public Task(String one, int count) {
			this.one = one;
			this.count = count;
		}

		@Override
		public void run() {

			int i = 0;
			while (i < 10) {
				synchronized (Task.class) {
					if (num % 3 == count) {
						num++;
						System.out.println(one);
					} else {
						System.out.println(one + "==============continue");
						continue;
					}
				}
				i++;
			}
		}
	}
}
