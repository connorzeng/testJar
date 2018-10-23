package com.connor.basic.jdk8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


import com.google.common.collect.Lists;

public class CollectionsSort {

	public static void main(String[] args) {

		Dog dog1 = new Dog(12, "小黄");
		Dog dog2 = new Dog(1, "小黄");
		Dog dog3 = new Dog(5, "小黄");
		Dog dog4 = new Dog(1, "小黄");
		Dog dog5 = new Dog(1, "小黄");

		List<Dog> dogs = new ArrayList<>();
		dogs.add(dog1);
		dogs.add(dog2);
		dogs.add(dog3);
		dogs.add(dog4);
		dogs.add(dog5);

		// 使用匿名方法
		/*
		 * Collections.sort(dogs, new Comparator<Dog>() {
		 * 
		 * @Override public int compare(Dog o1, Dog o2) { return
		 * Integer.valueOf(o1.getAge()).compareTo(o2.getAge()); } });
		 */

		// 1.使用lambda表达式
		//Collections.sort(dogs, Comparator.comparing(Dog::getAge));
		// 2.使用lambda表达式
		Collections.sort(dogs, (h1,h2)->Integer.valueOf(h1.getAge()).compareTo(h2.getAge()));
		
		
		
		for (Dog dog : dogs) {
			System.out.println(dog);
		}

		List<Dog> humans = Lists.newArrayList(new Dog("Sarah", 10), new Dog("Jack", 12));
		Comparator<Dog> comparator = (h1, h2) -> h1.getName().compareTo(h2.getName());
		humans.sort(comparator.reversed());

		for (Dog dog : humans) {
			System.out.println(dog);
		}

	}
}

class Dog {
	private int age;

	private String name;
	
	public Dog(String name, int age) {
		super();
		this.age = age;
		this.name = name;
	}

	public Dog(int age, String name) {
		super();
		this.age = age;
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Dog [age=" + age + ", name=" + name + "]";
	}

}