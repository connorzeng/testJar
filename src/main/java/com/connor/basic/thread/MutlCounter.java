package com.connor.basic.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试synchronize ReentrantLock 
 * 在单列和多列下的锁安全表现
 * @author connor_zeng
 *
 */
public class MutlCounter {
	private final static MutlCounter mutlCount = new MutlCounter();
	private static MutlCounter mutlCountLazy;
	private static ReentrantLock staticLock = new ReentrantLock();
	private ReentrantLock  lock = new ReentrantLock();
	private static Integer syncLock = 0;
	private static Integer syncStaticLock = 0;
	private static Integer syncMethod = 0;
	private static Integer syncThis = 0;
	private static Integer syncStaticMethod = 0;
	private static Integer syncClass = 0;
	
	public static void main(String[] args) {
		
		synchronized(staticLock){
			
		}
		
		List<Thread> ls = new ArrayList<Thread>();
		for (int i=0; i<10000; i++){
			ThreadCount thread = new ThreadCount();
			ls.add(thread);
		}
		for (Thread thread : ls){
			thread.start();
		}
		
		for (Thread thread : ls){
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("syncClass: " + syncClass);
		System.out.println("syncStaticMethod: " + syncStaticMethod);
		System.out.println("syncStaticLock: " + syncStaticLock);

		System.out.println("syncMethod: " + syncMethod);
		System.out.println("syncThis: " + syncThis);
		System.out.println("syncLock: " + syncLock);

	}
	
	/**
	 * 
	 */
	public void countLock () {
		lock.lock();
		try{
			syncLock++;
		}finally{
			lock.unlock();
		}
	}
	public void countStaticLock () {
		staticLock.lock();
		try{
			syncStaticLock++;
		}finally{
			staticLock.unlock();
		}
	}
	
	
	
	/**
	 * 
	 */
	public synchronized void countSyncMethod () {
		syncMethod++;
	}
	public static synchronized void countSyncStaticMethod () {
		syncStaticMethod++;
	}
	
	public void countSyncThis () {
		synchronized (this) {
			syncThis++;
		}
	}
	public void countSyncClass () {
		synchronized (MutlCounter.class) {
			syncClass++;
		}
	}
	
	
	/**
	 * 饿汉模式,保证单例
	 */
	public static MutlCounter getSingleInstance(){
		return mutlCount;
	}
	/**
	 * 懒汉模式,静态方法加锁,保证单例
	 * @return
	 */
	public static synchronized MutlCounter getSingleInstanceLazy1(){
		if (mutlCountLazy == null){
			mutlCountLazy = new MutlCounter();
		}
		return mutlCountLazy;
	}
	/**
	 * 懒汉模式,双重加锁,保证单例
	 * @return
	 */
	public static MutlCounter getSingleInstanceLazy2(){
		if (mutlCountLazy == null){
			synchronized (MutlCounter.class) {
				if (mutlCountLazy == null){
					mutlCountLazy = new MutlCounter();
				}
			}
		}
		return mutlCountLazy;
	}
	/**
	 * 懒汉模式,静态内部类加载,保证单例
	 */
	private static class NewInstantce{
		private static final MutlCounter mutlCounter = new MutlCounter();
	}
	public static MutlCounter getSingleInstanceLazy3(){
		return NewInstantce.mutlCounter;
	}
	/**
	 * 懒汉模式,不做处理,线程不安全
	 */
	public static MutlCounter getSingleInstanceLazy4(){
		if (mutlCountLazy == null){
			mutlCountLazy = new MutlCounter();
		}
		return mutlCountLazy;
	}

	
	/**
	 * 
	 */
	public static MutlCounter getMulInstance(){
		return new MutlCounter();
	}
	
}
class ThreadCount extends Thread{
	
	@Override
	public void run() {
		MultCount();
//		singCount();
	}
	
	public void singCount(){
		MutlCounter.getSingleInstance().countSyncClass();
		MutlCounter.getSingleInstance().countSyncMethod();
		MutlCounter.getSingleInstance().countSyncThis();
		MutlCounter.countSyncStaticMethod();
		MutlCounter.getSingleInstance().countLock();
		MutlCounter.getSingleInstance().countStaticLock();
	}
	
	public void MultCount(){
		MutlCounter.getMulInstance().countSyncClass();
		MutlCounter.getMulInstance().countSyncMethod();
		MutlCounter.getMulInstance().countSyncThis();
		MutlCounter.countSyncStaticMethod();
		MutlCounter.getMulInstance().countLock();
		MutlCounter.getMulInstance().countStaticLock();
	}
	
}
