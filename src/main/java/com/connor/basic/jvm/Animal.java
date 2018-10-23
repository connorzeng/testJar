package com.connor.basic.jvm;

public class Animal{

	//final类不会加载类,编译
	public static final int FINAL_VALUE = 123;
	//使用static会加载值
	public static int VALUE = 123;

	public void finalMethod(String string){
		
		String classLoaderName = this.getClass().getClassLoader().getClass().getName();
		System.out.println("classLoaderName:" + classLoaderName);
		
		Dog doger = new Dog();
		//hello
		System.out.println("find method" + string);
	}


	static{
		System.out.println("Animal static");
	}
	{
		System.out.println("Animal normal");
	}

	public Animal() {
		System.out.println("Animal construct");
	}

	public static void main(String[] args) {
		Dog dog = new Dog();
	}

}
