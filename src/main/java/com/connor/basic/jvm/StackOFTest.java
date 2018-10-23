package com.connor.basic.jvm;
/**
 * 栈内存溢出
 * 1.单个栈内存溢出
 * 2.总栈内存溢出
 * @author connor_zeng
 *
 */
public class StackOFTest {
	
	private int stackLength = 1;
	
	public static void main(String[] args) {
		
		StackOFTest st = new StackOFTest();
		//单个栈内存溢出
		/*
		try {
			st.stackLeak();
		} catch (Throwable e) {
			e.printStackTrace();
			System.out.println(st.stackLength);
		}*/
		
		//总栈内存溢出
		try {
			st.stackLeakByThread();
		} catch (Throwable e) {
			System.out.println("总栈内存溢出");
		}
	}
	
	
	/**
	 * 1.单个栈内存溢出
	 */
	public void stackLeak(){
		stackLength++;
		stackLeak();
	}
	
	/**
	 * 总栈内存溢出
	 */
	public void stackLeakByThread(){
		while (true){
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					dontStop();
				}
			});
			thread.start();
		}
	}
	public void dontStop(){
		while(true){
			
		}
	}
}
