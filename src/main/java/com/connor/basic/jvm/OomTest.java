package com.connor.basic.jvm;

import java.nio.ByteBuffer;
import java.util.LinkedList;

/**
 * 
 * 测试内存溢出 1.堆内存溢出 2.直接内存溢出
 * 
 * @author connor_zeng
 *
 */
public class OomTest {

	private static LinkedList<String> list;

	public static void main(String[] args) {
		
		//堆内存溢出
		//oomHeap();
		
		//直接内存溢出
		dom();
	}

	/**
	 * 堆内存溢出
	 */
	public static void oomHeap() {
		list = new LinkedList<>();
		while (true) {
			list.add("hello");
		}
	}
	/**
	 * 直接内存溢出
	 */
	public static void dom() {
		for (int i = 0; i < 1024; i++) {
			ByteBuffer.allocateDirect(1024 * 1024);
			System.out.println(i);
			//System.gc();
		}
	}

}
