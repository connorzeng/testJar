package com.connor.basicTest;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class TestAllInOne {

	public static void main(String[] args) {
    	int i = 0;
    	switch (4) {
		case 1:
			i++;
		case 2:
			i++;
		case 3:
			i++;
		case 4:
			i++;
			break;
		default:
			i++;
			break;
		}
    	System.out.println(i);
        System.out.println(TestStringTokenizer.getTokens("hello beautiful world"));
        
        TestI d = new TestI("zenggang", 1);
        TestI f = d;
        System.out.println(TestI.getSingle().hashCode());//单例模式下所获取的HashCode是相同的
        
    }
}

class TestStringTokenizer {

	public static String[] getTokens(String str, String delim) {
		StringTokenizer strTokenizer = new StringTokenizer(str, delim);
		List<String> list = new ArrayList<String>();
		while (strTokenizer.hasMoreTokens()) {
			list.add(strTokenizer.nextToken());
		}
		return (String[]) list.toArray();
	}

	public static String[] getTokens(String str) {
		StringTokenizer strTokenizer = new StringTokenizer(str);
		List<String> list = new ArrayList<String>();
		while (strTokenizer.hasMoreTokens()) {
			list.add(strTokenizer.nextToken());
		}
		return list.toArray(new String[list.size()]);
	}
}

/**
 * 测试单例模式HashCode
 * 
 * @author zenggang
 */
class TestI {
	private static TestI single = new TestI("hello", 1);
	private String name;
	private int age;

	public TestI(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public static TestI getSingle() {
		return single;
	}
}
