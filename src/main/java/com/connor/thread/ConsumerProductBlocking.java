package com.connor.thread;

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
		SenderBlocking sender = new SenderBlocking(blockingQueue);
		ReaderBlocking reader = new ReaderBlocking(blockingQueue);
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

class ReaderBlocking implements Runnable {

	private BlockingQueue<Character> blockingQueue;

	public ReaderBlocking(BlockingQueue<Character> blockingQueue) {
		this.blockingQueue = blockingQueue;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Character take = blockingQueue.take();
				System.out.println(take);
			} catch (InterruptedException e) {
				System.out.println("我是 reader,我被打断了");
				break;//必须加break,不然不会无线循环
			}
		}
	}
}

class SenderBlocking implements Runnable {

	private BlockingQueue<Character> blockingQueue;

	public SenderBlocking(BlockingQueue<Character> blockingQueue) {
		this.blockingQueue = blockingQueue;
	}

	@Override
	public void run() {
		// 通过管道写入
		while (true) {
			try {
				for (char c = 'A'; c < 'Z'; c++) {
					blockingQueue.put(c);
					TimeUnit.SECONDS.sleep(2);
				}
			} catch (InterruptedException e) {
				System.out.println("我是sender,我被打断了");
				break;
			}
		}
	}
}