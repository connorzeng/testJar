package com.connor.basic.jvm;

/**
 * java是值传递
 * @author connor_zeng
 */
public class TestValuePass {

	public static void main(String[] args) {
		StringBuffer sb = new StringBuffer("Hello ");
		System.out.println("Before change, sb = " + sb);
		changeData(sb);
		System.out.println("After changeData(n), sb = " + sb);

		StringBuffer sb2 = new StringBuffer("Hello ");
		System.out.println("Before change, sb2 = " + sb2);
		changeData2(sb2);
		System.out.println("After changeData(n), sb2 = " + sb2);
		
		Connor connor = new Connor("曾罡");
		System.out.println("Before change, connor = " + connor);
		changeConnorName(connor);
		System.out.println("After changeConnorName(n), connor = " + connor);
		
		
		int num = 4;
		System.out.println("Before change, num = " + num);
		changeInt(num);
		System.out.println("After changeInt(n), num = " + num);
		
	}
	
	public static void changeInt(int i){
		i = 3;
	}

	public static void changeData(StringBuffer strBuf) {
		strBuf.append("World!");
	}

	public static void changeData2(StringBuffer strBuf) {
		strBuf = new StringBuffer("Hi ");
		strBuf.append("World!");
	}
	
	public static void changeConnorName(Connor connor){
		
		connor.setName("Connor");
	}
	
}

class Connor{
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Connor(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Connor [name=" + name + "]";
	}
}
