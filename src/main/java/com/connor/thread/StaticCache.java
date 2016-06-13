package com.connor.thread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/**
 * 读写锁测试
 * @author connor_zeng
 *
 */
public class StaticCache {
	
	private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	private static final ReentrantReadWriteLock.WriteLock w = lock.writeLock();
	private static final ReentrantReadWriteLock.ReadLock r = lock.readLock();
	private static final Map<String,Object> cache = new HashMap<String, Object>();
	
	static{
		cache.put("name", "丑小鸭");
	}
	
	public static void main(String[] args) {
		
		System.out.println(StaticCache.getName());
		
		/*List<Thread> ls = new ArrayList<Thread>();
		for (int i=0; i<10000; i++){
			ThreadName thread = new ThreadName();
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
		}*/
		
	}
	
	
private static String getName() {
	
	try{
		return "name";//在执行返回
	}finally{
		System.out.println("before");//先执行打印1
	}
}


	public static Object readWithLock(String key){
		
		r.lock();
		
		return cache.get(key);
	}
	public static void writeWithLock(String key,Object value){
		cache.put(key, value);
	}
	
}

class ThreadName extends Thread{
	@Override
	public void run() {
		System.out.println(StaticCache.readWithLock("name"));
	}
}

