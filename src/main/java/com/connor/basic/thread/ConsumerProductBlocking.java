package com.connor.basic.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞队列实现生产者消费者
 * 
 * @author connor_zeng
 *
 */
public class ConsumerProductBlocking {

	public static void main(String[] args) {
		
		BlockingQueue<Character> blockingQueue = new ArrayBlockingQueue<Character>(1);
		ExecutorService exec = Executors.newCachedThreadPool();
		SenderMan sender = new SenderMan(blockingQueue);
		ReaderMan reader = new ReaderMan(blockingQueue);
		exec.execute(reader);
		exec.execute(sender);

		try {
			TimeUnit.MILLISECONDS.sleep(5000l);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		exec.shutdownNow();
	}

}

class ReaderMan implements Runnable {

	private BlockingQueue<Character> blockingQueue;

	public ReaderMan(BlockingQueue<Character> blockingQueue) {
		this.blockingQueue = blockingQueue;
	}

	@Override
	public void run() {
		while (true) {
			try {
				System.out.println("reader begin to reader");
				Character take = blockingQueue.take();
				System.out.println("reader get char:" + take);
			} catch (InterruptedException e) {
				System.out.println("我是 reader,我被打断了");
				break;//必须加break,不然不会无线循环
			}
		}
	}
}

class SenderMan implements Runnable {

	private BlockingQueue<Character> blockingQueue;

	public SenderMan(BlockingQueue<Character> blockingQueue) {
		this.blockingQueue = blockingQueue;
	}

	@Override
	public void run() {
		// 通过管道写入
		while (true) {
			try {
				System.out.println("sender begin to send");
				for (char c = 'A'; c < 'Z'; c++) {
					blockingQueue.put(c);
					TimeUnit.SECONDS.sleep(3);
				}
			} catch (InterruptedException e) {
				System.out.println("我是sender,我被打断了");
				break;
			}
		}
	}
}