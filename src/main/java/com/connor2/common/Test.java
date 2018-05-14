package com.connor2.common;

import java.util.LinkedList;
import java.util.List;

public class Test {
	private static final int PAGE_SIZE = 200;
	private static final int nums = 1;
	
	public static void main(String[] args) {
		
		
		Double gogogo = 128.1;
		Double gogogo1 = 128.1;
		System.out.println(gogogo == gogogo1);
		
		
		String aa = "aaaaa";
		String bb = "aaaaa";
		aa.concat("ccc");
		System.out.println(aa);
		aa.toUpperCase();
		aa +="A";
		System.out.println(bb);
		
		int j = 20;
		
		j += j-=j;
		System.out.println(j);
		double a = 111D;
		float dd = 1.1F;
		
		List<Object> ab = new LinkedList<>();
		ab.add(1);
		ab.add(2);
		ab= ab.subList(0, 1);
		
		
		Test t = new Test();
		t.testFinal();
		
		//System.out.println(t.caculatePages(nums, PAGE_SIZE));
		int i = 0;
		for (int page=0;page<t.caculatePages(nums, PAGE_SIZE);page++){
			System.out.println(page*PAGE_SIZE+1 + " : " +(page+1)*PAGE_SIZE);
		}
		
		Integer a3 = null;
		System.out.println(a);
		
		
	}
	
	private long caculatePages(long netNums, int pageSize) {
		if (netNums <= 0){
			return 0;
		} else if (netNums > 0 && netNums <= pageSize){
			return 1;
		} else {
			return netNums % pageSize == 0 ? netNums/pageSize : netNums/pageSize + 1;
		}
	}
	
	public void testFinal(){
		
		try{
			return ;
		}catch(Exception e){
			e.printStackTrace();
		} finally {
			System.out.println("hello finally");
		}
	}
	
}
