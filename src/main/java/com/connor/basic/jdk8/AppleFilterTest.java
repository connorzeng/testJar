package com.connor.basic.jdk8;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 使用lambda表达式来测试Filter
 * 
 * @author gangg
 *
 */
public class AppleFilterTest {

	public static void main(String[] args) {

		List<Apple> inventory = new ArrayList<>();
		inventory.add(new Apple(10.1, "green"));
		inventory.add(new Apple(10.1, "green"));
		inventory.add(new Apple(10.3, "red"));
		inventory.add(new Apple(11.1, "green"));
		inventory.add(new Apple(11.2, "green"));
		inventory.add(new Apple(11.3, "red"));

		// 1.通过参数来传递lambda函数
		List<Apple> result = filterApple(inventory, Apple::isGreen);
		// 2.通过参数来传递匿名lambada函数
		List<Apple> weightResult = filterApple(inventory,
				(Apple a) -> "green".equals(a.getColor()) && a.getWeight() < 11);
		
		// 3.使用steam lambada
		List<Apple> weightResult2 = inventory.stream().filter((Apple a) -> "green".equals(a.getColor()) && a.getWeight() < 11).collect(Collectors.toList());

		System.out.println(result.size());
		System.out.println(weightResult.size());
		System.out.println(weightResult2.size());
			
		new Thread(() -> System.out.println("lambada gogogo")).start();
	}

	public static List<Apple> filterApple(List<Apple> inventory, Condition<Apple> condition) {

		List<Apple> result = new ArrayList<>(16);
		for (Apple apple : inventory) {
			if (condition.isQualified(apple)) {
				result.add(apple);
			}
		}
		return result;
	}
}

class Apple {

	private double weight;

	private String color;

	public Apple(double weight, String color) {
		super();
		this.weight = weight;
		this.color = color;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public boolean isGreen() {
		if ("green".equals(color)) {
			return true;
		}
		return false;
	}

}