package com.connor.basic.jvm;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

import com.connor.util.SleepUtils;

/**
 * 软引用示例
 * @author connor_zeng
 *
 */
public class TestSoftReference {
	
	public static void main(String[] args) {
		
		String a = new String("hello1");
		SoftReference<String> sra = new SoftReference<String>(a);
		WeakReference<String> wra = new WeakReference<String>(a);
		a = null;
		System.gc();
		
		if(sra.get() != null){
			System.out.println("not clear");
			a = sra.get();
		} else {
			System.out.println("i am been clear");
			a = new String("hello2");
			sra = new SoftReference<String>(a);
		}
		
		if(wra.get() != null){
			System.out.println("not clear");
			a = wra.get();
			System.out.println(a);
		} else {
			System.out.println("i am been clear");
			a = new String("hello2");
			wra = new WeakReference<String>(a);
		}
		
	}
}
