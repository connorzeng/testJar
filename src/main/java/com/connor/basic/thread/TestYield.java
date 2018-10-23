package com.connor.basic.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 测试yield
 * 该方法与sleep()类似，只是不能由用户指定暂停多长时间，并且yield（）方法只能让同优先级的线程有执行的机会。
 * @author connor_zeng
 */
public class TestYield {
	
	public static void main(String[] args) {
		ExecutorService exeServices = Executors.newFixedThreadPool(4);
		
		Thread t1 = new Thread(new TestYieldRunner(),"t1");
		Thread t2 = new Thread(new TestYieldRunner(),"t2");
		Thread t3 = new Thread(new TestYieldRunner(),"t3");
		Thread t4 = new Thread(new TestYieldRunner(),"t4");
		Thread t5 = new Thread(new TestYieldRunner(),"t5");
		Thread t6 = new Thread(new TestYieldRunner(),"t6");
		exeServices.submit(t1);
		exeServices.submit(t2);
		exeServices.submit(t3);
		exeServices.submit(t4);
		exeServices.submit(t5);
		exeServices.submit(t6);
		exeServices.shutdown();
	}
}
class TestYieldRunner implements Runnable{
	@Override
	public void run() {
		//yield不会让出锁
		synchronized (TestYieldRunner.class) {
			for(int i = 0; i<100;i++){
				System.out.println(Thread.currentThread().getName());
				Thread.yield();
			}
		}
	}
}