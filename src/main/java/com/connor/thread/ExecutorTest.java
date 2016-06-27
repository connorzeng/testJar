package com.connor.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.util.SleepUtils;

/**
 * 测试Executor框架
 * @author connor_zeng
 */
public class ExecutorTest {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		
		//1.1.大小无界的线程池 轻负载,短期异步任务, 使用没有容量的队列SynchronousQueue
		ExecutorService cachedThreadPool =  Executors.newCachedThreadPool();
		
		
		//1.2.固定线程,重负载服务器, 使用无界队列 LinkedBlockingQueue
		ThreadPoolExecutor fixedThreadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
		
		//1.3.单线程 ,使用无界队列 LinkedBlockingQueue
		ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
		
		//2.1.无界延期执行
		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
		
		
		System.out.println("=============使用使用ExcutorService执行==============");
		/**
		 * 使用ExcutorService执行
		 */
		Future<String> future = singleThreadPool.submit(new Eat());
		Future future2 = singleThreadPool.submit(new Eat2());
		//future.cancel(true);
		System.out.println(future.get());
		System.out.println(future2.get());
		
		System.out.println("=============使用FutureTask 执行==============");
		/**
		 * 使用FutureTask 执行
		 */
		FutureTask<String> task = new FutureTask<String>(new Eat());
		task.run();
		System.out.println(task.get());
		String a = "";
		FutureTask<String> task2 = new FutureTask<String>(new Eat2(),a);
		task2.run();
		System.out.println(task2.get());
		System.out.println("============使用Thread 执行===============");
		/**
		 * 使用Thread 执行
		 */
		FutureTask<String> threadTask = new FutureTask<String>(new Eat());
		Thread thread = new Thread(threadTask);
		thread.run();
		System.out.println(threadTask.get());
		
		/**
		 * 测试饱和策略,Exception in thread "main" java.util.concurrent.RejectedExecutionException 
		 * AbortPolicy
		 */
		ThreadPoolExecutor exes = new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
		Eat2 e1 = new Eat2();
		Eat2 e2 = new Eat2();
		Eat2 e3 = new Eat2();
		exes.execute(e1);
		exes.execute(e2);
		exes.execute(e3);
	}
}
/**
 * 
 * @author connor_zeng
 */
class Eat implements Callable{

	@Override
	public String call() throws Exception {
		SleepUtils.killTime(2);
		System.out.println("我要回去了");
		return "i am back";
	}
}
/**
 * 
 * @author connor_zeng
 */
class Eat2 implements Runnable{
	@Override
	public void run() {
		SleepUtils.killTime(2);
		System.out.println("我要回去了");
	}
}