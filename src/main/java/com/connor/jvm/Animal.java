package com.connor.jvm;

public class Animal{
	static{
		System.out.println("父类初始化");
	}
	public static final int FINAL_VALUE = 123;
	public static int VALUE = 123;
	public void finalMethod(String string){
		//hello
		System.out.println("find method" + string);
	}
	
}
