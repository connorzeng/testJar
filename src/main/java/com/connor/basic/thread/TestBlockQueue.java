package com.connor.basic.thread;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 测试BlockQueue
 */
public class TestBlockQueue {
	
	public static void main(String[] args) throws InterruptedException {
		
		ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<String>(2);
		//抛出异常  
		arrayBlockingQueue.add("add hello");
		arrayBlockingQueue.remove();
		arrayBlockingQueue.element();
		//一直阻塞
		arrayBlockingQueue.put("put hello");
		arrayBlockingQueue.take();
		//返回特殊值||超时退出
		arrayBlockingQueue.offer("offer hello");
		arrayBlockingQueue.poll();
		arrayBlockingQueue.peek();
		
	}
}
