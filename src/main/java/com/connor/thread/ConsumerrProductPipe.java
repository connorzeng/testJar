package com.connor.thread;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 线程之间pipe通信, 可以看做是生产者消费者的变种问题
 * 使用BlockingQueue将更加健壮
 * 
 * PipeReader与普通IO之间最重要的差异就是可中断的
 * @author connor_zeng
 */
public class ConsumerrProductPipe {

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		Sender sender = new Sender();
		Reader reader = new Reader(sender);
		exec.execute(sender);
		exec.execute(reader);

	}
}

class Reader implements Runnable {

	private PipedReader reader = null;
	
	
	/**
	 * 特点（缺陷）：必须建立一个Sender(writer)与Reader相关联
	 * @param sender
	 */
	public Reader(Sender sender) {
		try {
			reader = new PipedReader(sender.getWrite());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// 通过管道读取
		try {
			while (true) {
				char[] chars = new char[1];
				reader.read(chars);
				System.out.println(chars);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class Sender implements Runnable {

	PipedWriter write = new PipedWriter();

	public PipedWriter getWrite() {
		return write;
	}

	@Override
	public void run() {
		// 通过管道写入
		try {
			while (true) {
				for (char c = 'A'; c < 'Z'; c++) {
					write.write(c);
					TimeUnit.MICROSECONDS.sleep(500);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {

		}
	}
}