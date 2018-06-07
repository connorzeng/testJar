package com.connor.jvm;

public class StringTest {
	private static int time = 50000;

	public static void main(String[] args) {
		testString();
		testStringBuffer();
		testStringBuilder();
		test1String();
		test2String();
	}

	public static void testString() {
		String s = "";
		long begin = System.currentTimeMillis();
		for (int i = 0; i < time; i++) {
			s += "java";
		}
		long over = System.currentTimeMillis();
		System.out.println("操作" + s.getClass().getName() + "类型使用的时间为：" + (over - begin) + "毫秒");
	}

	public static void testStringBuffer() {
		StringBuffer sb = new StringBuffer();
		long begin = System.currentTimeMillis();
		for (int i = 0; i < time; i++) {
			sb.append("java");
		}
		long over = System.currentTimeMillis();
		System.out.println("操作" + sb.getClass().getName() + "类型使用的时间为：" + (over - begin) + "毫秒");
	}

	public static void testStringBuilder() {
		StringBuilder sb = new StringBuilder();
		long begin = System.currentTimeMillis();
		for (int i = 0; i < time; i++) {
			sb.append("java");
		}
		long over = System.currentTimeMillis();
		System.out.println("操作" + sb.getClass().getName() + "类型使用的时间为：" + (over - begin) + "毫秒");
	}

	public static void test1String() {
		long begin = System.currentTimeMillis();
		for (int i = 0; i < time; i++) {
			String s = "I" + "love" + "java";
		}
		long over = System.currentTimeMillis();
		System.out.println("字符串直接相加操作：" + (over - begin) + "毫秒");
	}

	public static void test2String() {
		String s1 = "I";
		String s2 = "love";
		String s3 = "java";
		long begin = System.currentTimeMillis();
		for (int i = 0; i < time; i++) {
			String s = s1 + s2 + s3;
		}
		long over = System.currentTimeMillis();
		System.out.println("字符串间接相加操作：" + (over - begin) + "毫秒");
	}
}
