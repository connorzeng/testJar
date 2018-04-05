package com.connor.jvm;

public class Dog extends Animal{
	static{
		System.out.println("Dog static");
	}
	{
		System.out.println("Dog normal");
	}

	public Dog() {
		System.out.println("Dog construct");
	}

	public static void main(String[] args) {
		/*Animal static
		Dog static
		Animal normal
		Animal construct
		Dog normal
		Dog construct*/
		Dog dog = new Dog();
	}
}
