package com.connor.basic.jdk8;

import java.util.Arrays;
import java.util.List;

public class TestSteam {

	public static void main(String[] args) {
		List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
		double total = 0;
		for (Integer cost : costBeforeTax) {
			double price = cost + .12 * cost;
			total = total + price;
		}
		System.out.println("Total : " + total);

		// 新方法：
		double bill = (double) costBeforeTax.stream().map((cost) -> cost + .12 * cost).reduce((sum, cost) -> sum + cost).get();
		System.out.println("Total : " + bill);
	}
}
